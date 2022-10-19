package es.travelworld.ejercicio13_retrofit.repository;

import android.util.Log;

import es.travelworld.ejercicio13_retrofit.domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    public interface Login{
        void onSuccess(User user);
        void onError(Throwable throwable);
    }

    public void getUserFromServer(String username, String password, Login callback){
        Utils.getApiLogin().listRepos(username,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful() && response.code() == 200) {
                    callback.onSuccess(response.body());
                    Log.i("LoginRepository","Ha entrado en success");
                }else{
                    callback.onError(new Throwable("Ha habido respuesta pero no ha sido 200 o no fue exitosa"));
                    Log.e("LoginRepository","Ha entrado en error");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t);
                Log.i("LoginRepository","Ha entrado en failure");
            }
        });
    }
}
