package com.gaurav.weatherforecastapp.ui.currentweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.gaurav.weatherforecastapp.R;

import butterknife.ButterKnife;

public class CurrentWeatherFragment extends Fragment {

    private CurrentWeatherViewModel currentWeatherViewModel;
    private NavController navController;


    private String cityName = "Bangalore";
    private String weatherApiKey ="2de26709fcc9817162aa9909b587d145";


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        currentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        currentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);




        View currentWeatherView = inflater.inflate(R.layout.fragment_current_weather, container, false);

        ButterKnife.bind(this,currentWeatherView);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cityName);

        return currentWeatherView;
    }
}