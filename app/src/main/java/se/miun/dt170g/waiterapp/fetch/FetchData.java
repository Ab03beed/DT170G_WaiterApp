package se.miun.dt170g.waiterapp.fetch;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.POST;
import retrofit2.http.Path;
import se.miun.dt170g.waiterapp.class_models.ALaCarteModel;
import se.miun.dt170g.waiterapp.class_models.DrinkModel;
import se.miun.dt170g.waiterapp.class_models.OrderDTO;
import se.miun.dt170g.waiterapp.class_models.OrderModel;
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

    @POST("order")
    Call<Void> addOrder(@Body OrderDTO newOrder);

    @PUT("tables/{id}")
    Call<Void> updateTableStatus(@Path("id") int tableId, @Body TableModel tableModel);
}


