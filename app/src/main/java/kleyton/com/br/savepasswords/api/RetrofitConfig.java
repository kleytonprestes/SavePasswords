package kleyton.com.br.savepasswords.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static final String URL_BASE = "https://dev.people.com.ai/mobile/api/v2/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static Retrofit  getInstance() {
        return retrofit;
    }

    public static ServiceApi getService(){

        retrofit = getInstance();
        return retrofit.create(ServiceApi.class);
    }

}
