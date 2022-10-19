package es.travelworld.ejercicio13_retrofit.repository;

import android.util.Log;

import es.travelworld.ejercicio13_retrofit.domain.HotelsList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsListRepository {
    public interface HotelsListInterface{
        void onSuccess(HotelsList hotelsList);
        void onError(Throwable throwable);
    }



    public void getHotelsListFromServer(HotelsListInterface callback){
        Utils.getApiHotelsList().listRepos().enqueue(new Callback<HotelsList>() {
            @Override
            public void onResponse(Call<HotelsList> call, Response<HotelsList> response) {
                if(response.isSuccessful() && response.code() == 200) {
                    callback.onSuccess(response.body());
                    Log.i("HotelsListRepository","Ha entrado en success");
                }else{
                    callback.onError(new Throwable("Ha habido respuesta pero no ha sido 200 o no fue exitosa"));
                    Log.e("HotelsListRepository","Ha entrado en error");
                }
            }

            @Override
            public void onFailure(Call<HotelsList> call, Throwable t) {
                callback.onError(t);
                Log.i("HotelsListRepository","Ha entrado en failure");
            }
        });
    }
}
