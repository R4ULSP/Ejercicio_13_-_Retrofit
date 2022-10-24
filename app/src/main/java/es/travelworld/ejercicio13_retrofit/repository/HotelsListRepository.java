package es.travelworld.ejercicio13_retrofit.repository;


import androidx.annotation.NonNull;

import es.travelworld.ejercicio13_retrofit.domain.HotelsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsListRepository {
    public interface HotelsListInterface {
        void onSuccess(HotelsList hotelsList);

        void onError(Throwable throwable);
    }

    public void getHotelsListFromServer(HotelsListInterface callback) {
        Utils.getApiHotelsList().listRepos().enqueue(new Callback<HotelsList>() {
            @Override
            public void onResponse(@NonNull Call<HotelsList> call, @NonNull Response<HotelsList> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Ha habido respuesta pero no ha sido 200 o no fue exitosa"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<HotelsList> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }
}
