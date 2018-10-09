package kleyton.com.br.savepasswords.api;

import kleyton.com.br.savepasswords.signup.model.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("logo/{siteUrl}")
    Call<ResponseBody> getLogo(@Path("siteUrl") String siteUrl);

    @POST("login")
    Call<ResponseBody> loginUser(@Header("Content-Type") String contentType,
                                 @Body RequestBody user);

    @POST("register")
    Call<ResponseBody> signUp(@Header("Content-Type") String contentType,
                      @Body RequestBody user);






}
