package se.miun.dt170g.waiterapp.fetch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import se.miun.dt170g.waiterapp.class_models.ALaCarteModel;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.TableModel;

public interface FetchData {

    @GET("tables")
    Call<ArrayList<TableModel>> getTables();

    @GET("a_la_carte")
    Call<ArrayList<ALaCarteModel>> getA_LA_CARTE_ITEMS();

    @GET("drinks")
    Call<ArrayList<DrinkModel>> getDrinks();

    @POST("drinks")
    Call<Void> addDrink(@Body DrinkModel newDrink);


}


