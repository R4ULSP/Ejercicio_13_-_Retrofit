package es.travelworld.ejercicio13_retrofit.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import com.travelworld.ejercicio13_retrofit.R;
import com.travelworld.ejercicio13_retrofit.databinding.FragmentLoginBinding;

import es.travelworld.ejercicio13_retrofit.domain.User;
import es.travelworld.ejercicio13_retrofit.repository.LoginRepository;
import es.travelworld.ejercicio13_retrofit.view.vm.LoginViewModel;


//TODO: Login mediante Retrofit => Si devuelve un error deberemos indicar al usuario mediante un SnackBar que no hemos podido acceder
public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding binding;
    private User user;
    private LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new LoginViewModel.Factory(new LoginRepository())).get(LoginViewModel.class);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //user = new User("1234","5678","18-99","1234");

        if (user != null) {
            Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();
        }

        setListeners();
        return view;
    }

    private void setListeners() {
        binding.loginForgotPasswordButton.setOnClickListener(this);
        binding.loginNewAccountButton.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);

        binding.loginInputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });

        binding.loginInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
    }

    private void validateForm() {
        binding.loginButton.setEnabled(false);
        boolean userValidation = false;
        boolean passwordValidation = false;

        if (binding.loginInputUser.getText() != null && !binding.loginInputUser.getText().toString().equals("")) {
            userValidation = true;
        }
        if (binding.loginInputPassword.getText() != null && !binding.loginInputPassword.getText().toString().equals("")) {
            passwordValidation = true;
        }

        if (userValidation && passwordValidation) {
            binding.loginButton.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.loginForgotPasswordButton.equals(view)) {
            Snackbar.make(binding.getRoot(), R.string.wip_feature, BaseTransientBottomBar.LENGTH_LONG).show();
        } else if (binding.loginNewAccountButton.equals(view)) {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.to_registerFragment);
        } else if (binding.loginButton.equals(view)) {
            login();
        }
    }

    private void login() {
        loginViewModel.login(Objects.requireNonNull(binding.loginInputUser.getText()).toString(), Objects.requireNonNull(binding.loginInputPassword.getText()).toString());
        loginViewModel.getUserLogin().observe(this, user1 -> {
            LoginFragmentDirections.ToHomeActivity directions = LoginFragmentDirections.toHomeActivity().setLoginUser(user1);
            Navigation.findNavController(requireView()).navigate(directions);
        });

        loginViewModel.getThrowable().observe(this, throwable -> {
            Log.e("MainActivity", "Error en : " + throwable.getMessage());
            showLoginErrorDialog();
        });
    }

    private void showLoginErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.login_error)
                .setMessage(R.string.login_error_message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) ->
                                dialogInterface.dismiss()
                        //Click salir
                );

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}