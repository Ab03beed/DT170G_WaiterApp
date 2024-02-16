package se.miun.dt170g.waiterapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import se.miun.dt170g.waiterapp.class_models.ALaCarteItem;

public interface FetchData {

    ArrayList<ALaCarteItem> A_LA_CARTE_ITEMS = new ArrayList<>();

    @GET("a_la_carte")
    Call<ArrayList<ALaCarteItem>> get_A_LA_CARTE_ITEMS();

    @GET("drinks")
    Call<ArrayList<ALaCarteItem>> get_drinks();




}
