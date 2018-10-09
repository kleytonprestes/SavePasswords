package kleyton.com.br.savepasswords.signup.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kleyton.com.br.savepasswords.api.BaseSync;
import kleyton.com.br.savepasswords.api.RetrofitConfig;
import kleyton.com.br.savepasswords.api.ServiceApi;
import kleyton.com.br.savepasswords.api.SyncInterface;
import kleyton.com.br.savepasswords.utils.Utils;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpRequest extends BaseSync {

    private SyncInterface syncInterface;
    private User user;


    public SignUpRequest(SyncInterface syncInterface, User user) {
        this.syncInterface = syncInterface;
        this.user = user;
    }

    @Override
    public void startSync() {
        ServiceApi api = RetrofitConfig.getService();

        String json =  null;
        try {
            json = getStringJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(json != null && !json.equals("[]")) {

            RequestBody request = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

            Call<ResponseBody> userCall = api.signUp("application/json", request);

            userCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null) {

                        try {

                            String body = response.body().string().trim();

                            JSONObject jsonObject;

                            jsonObject = new JSONObject(body);

                            Gson gson = new Gson();

                            SignUpResponse signUpResponse = gson.fromJson(jsonObject.toString(), SignUpResponse.class);

                            Utils.token = signUpResponse.getToken();

                            syncInterface.onSuccessSync();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        syncInterface.onFailureSync();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    syncInterface.onFailureSync();
                }
            });
        }
    }

    @Override
    public void onSuccessSync() {
        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }

    private String getStringJson() throws JSONException {

        Gson gson = new Gson();
        String jsonFromGson = gson.toJson(user);

        JSONObject obj = new JSONObject(jsonFromGson);

        obj.put("email", user.getEmail());
        obj.put("name", user.getName());
        obj.put("password", user.getPassword());

        return obj.toString();
    }

}
