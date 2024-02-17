package se.miun.dt170g.waiterapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.miun.dt170g.waiterapp.class_models.ALaCarteItem;
import se.miun.dt170g.waiterapp.class_models.TableItem;

public interface FetchData {

    @GET("tables")
    Call<ArrayList<TableItem>> getTables();

    @GET("a_la_carte")
    Call<ArrayList<ALaCarteItem>> get_A_LA_CARTE_ITEMS();

    @GET("drinks")
    Call<ArrayList<ALaCarteItem>> getDrinks();


}


