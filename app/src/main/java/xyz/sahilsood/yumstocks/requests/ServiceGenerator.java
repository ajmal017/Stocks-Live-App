package xyz.sahilsood.yumstocks.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl("https://interviews.yum.dev/")
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static YumStocksApi yumStocksApi = retrofit.create(YumStocksApi.class);

    public static YumStocksApi getYumStocksApi(){
        return yumStocksApi;
    }


}
