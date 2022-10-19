package es.travelworld.ejercicio13_retrofit.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.travelworld.ejercicio13_retrofit.databinding.FragmentHomeBinding;

import es.travelworld.ejercicio13_retrofit.repository.HotelsListRepository;
import es.travelworld.ejercicio13_retrofit.view.vm.HotelsListViewModel;


public class HomeFragment extends Fragment {

    private HotelsListViewModel hotelsListViewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hotelsListViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) new HotelsListViewModel.Factory(new HotelsListRepository())).get(HotelsListViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        hotelsListViewModel.retrieveHotelsList();
        hotelsListViewModel.getHotelsList().observe(getViewLifecycleOwner(),hotelsList -> {
            Log.w("HomeFragment","Prueba de hotel, cantidad: " + hotelsList.getHotels().size());
            Log.w("HomeFragment","Prueba de hotel, cantidad: " + hotelsList.getHotels().get(0).getName());

        });
        hotelsListViewModel.getThrowable().observe(getViewLifecycleOwner(),throwable -> {
            Log.e("HomeFragment", "Error en: " + throwable.getMessage());
        });

        return binding.getRoot();
    }
}