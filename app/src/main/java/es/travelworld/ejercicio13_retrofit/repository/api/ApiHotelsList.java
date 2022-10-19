package es.travelworld.ejercicio13_retrofit.repository.api;

import es.travelworld.ejercicio13_retrofit.domain.HotelsList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiHotelsList {
    /**
     * Obtener la lista de hoteles con GET
     */
    @Headers({"Accept: application/json"})
    @GET("listHotels")
    Call<HotelsList> listRepos();
}
