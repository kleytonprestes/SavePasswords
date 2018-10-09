package kleyton.com.br.savepasswords.passwordslist.model;

import kleyton.com.br.savepasswords.api.BaseSync;
import kleyton.com.br.savepasswords.api.RetrofitConfig;
import kleyton.com.br.savepasswords.api.ServiceApi;
import kleyton.com.br.savepasswords.api.SyncInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordListRequest extends BaseSync {

    private SyncInterface syncInterface;
    private String url;


    public PasswordListRequest(SyncInterface syncInterface, String url) {
        this.syncInterface = syncInterface;
        this.url = url;
    }

    @Override
    public void startSync() {
        ServiceApi api = RetrofitConfig.getService();

        Call<ResponseBody> userCall = api.getLogo(url);

        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    syncInterface.onSuccessSync();
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

    @Override
    public void onSuccessSync() {
        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }
}
