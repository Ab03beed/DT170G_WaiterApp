package se.miun.dt170g.waiterapp.fetch;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {


    private final String WS_HOST = "http://192.168.1.73:8080/projektDT170G-1.0-SNAPSHOT/api/";
    private Retrofit retrofit;



    public Retro() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    okhttp3.Request originalRequest = chain.request();
                    okhttp3.Request request = originalRequest.newBuilder()
                            .cacheControl(new CacheControl.Builder().noStore().build())
                            .build();
                    return chain.proceed(request);
                })
                .build();

         retrofit = new Retrofit.Builder()
                .baseUrl(WS_HOST)
                 .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
