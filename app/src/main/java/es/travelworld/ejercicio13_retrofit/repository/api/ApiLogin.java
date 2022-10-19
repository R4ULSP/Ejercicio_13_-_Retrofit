package es.travelworld.ejercicio13_retrofit.repository.api;

import es.travelworld.ejercicio13_retrofit.domain.User;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiLogin {
    /**
     * Obtener el login con un POST
     */
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<User> listRepos(@Query("usuario") String user
            , @Query("password") String password);

}
