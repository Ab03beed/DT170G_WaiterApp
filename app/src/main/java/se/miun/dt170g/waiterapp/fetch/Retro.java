package se.miun.dt170g.waiterapp.fetch;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {


    private final String WS_HOST = "http://10.82.234.67:8080/projektDT170G-1.0-SNAPSHOT/api/";
    private Retrofit retrofit;



    public Retro() {
         retrofit = new Retrofit.Builder()
                .baseUrl(WS_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
