package es.travelworld.ejercicio13_retrofit.view.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.travelworld.ejercicio13_retrofit.domain.User;
import es.travelworld.ejercicio13_retrofit.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private final LoginRepository repository;
    private final MutableLiveData<Throwable> throwableError = new MutableLiveData<>();
    private final MutableLiveData<User> userLogin = new MutableLiveData<>();

    public LoginViewModel(LoginRepository loginRepository) {
        this.repository = loginRepository;
    }

    public LiveData<Throwable> getThrowable() {
        return throwableError;
    }

    public LiveData<User> getUserLogin() {
        return userLogin;
    }

    public void login(String username, String password) {
        repository.getUserFromServer(username, password, new LoginRepository.Login() {
            @Override
            public void onSuccess(User user) {
                userLogin.setValue(user);
            }

            @Override
            public void onError(Throwable throwable) {
                throwableError.setValue(throwable);
            }
        });
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final LoginRepository loginRepository;

        public Factory(LoginRepository loginRepository) {
            this.loginRepository = loginRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(loginRepository);
        }
    }
}
