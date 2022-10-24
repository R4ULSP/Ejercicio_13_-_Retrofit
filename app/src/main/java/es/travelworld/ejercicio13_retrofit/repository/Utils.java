package es.travelworld.ejercicio13_retrofit.repository;

import es.travelworld.ejercicio13_retrofit.repository.api.ApiHotelsList;
import es.travelworld.ejercicio13_retrofit.repository.api.ApiLogin;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    private static final String HOST = "https://01394d44-8918-4a1d-8059-629c50c25e87.mock.pstmn.io/";

    private Utils(){
        //Private Constructor
    }

    public static ApiLogin getApiLogin() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create()) //Hace la conversion con gson
                .build();

        return retrofit.create(ApiLogin.class);
    }

    public static ApiHotelsList getApiHotelsList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiHotelsList.class);
    }
}
