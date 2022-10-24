package es.travelworld.ejercicio13_retrofit.repository;


import androidx.annotation.NonNull;

import es.travelworld.ejercicio13_retrofit.domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    public interface Login {
        void onSuccess(User user);

        void onError(Throwable throwable);
    }

    public void getUserFromServer(String username, String password, Login callback) {
        Utils.getApiLogin().listRepos(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Throwable("Ha habido respuesta pero no ha sido 200 o no fue exitosa"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }
}
