package es.travelworld.ejercicio13_retrofit.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travelworld.ejercicio13_retrofit.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.travelworld.ejercicio13_retrofit.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}