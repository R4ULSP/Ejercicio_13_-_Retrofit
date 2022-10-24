package es.travelworld.ejercicio13_retrofit.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.travelworld.ejercicio13_retrofit.R;
import com.travelworld.ejercicio13_retrofit.databinding.FragmentHomeBinding;

import java.util.List;
import java.util.Objects;

import es.travelworld.ejercicio13_retrofit.domain.Hotel;
import es.travelworld.ejercicio13_retrofit.repository.HotelsListRepository;
import es.travelworld.ejercicio13_retrofit.view.fragments.adapter.HotelListAdapter;
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
        hotelsListViewModel.getHotelsList().observe(getViewLifecycleOwner(), hotels -> {
            List<Hotel> hotelsList = hotels.getHotels();
            setUpRecyclerView(binding, hotelsList);
        });
        hotelsListViewModel.getThrowable().observe(getViewLifecycleOwner(), throwable -> {
            showHotelLoadErrorDialog();
            Log.e("HomeFragment", "Error en: " + throwable.getMessage());
        });

        return binding.getRoot();
    }

    private void showHotelLoadErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.hotels_error)
                .setMessage(R.string.hotels_load_error_message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) ->
                        dialogInterface.dismiss()
                        //Click salir
                );

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setUpRecyclerView(FragmentHomeBinding binding, List<Hotel> hotelsList) {
        binding.hotelList.setHasFixedSize(false);
        binding.hotelList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.hotelList.setAdapter(new HotelListAdapter(hotelsList, hotel -> Toast.makeText(requireActivity(), hotel.getName(), Toast.LENGTH_SHORT).show()));
    }
}