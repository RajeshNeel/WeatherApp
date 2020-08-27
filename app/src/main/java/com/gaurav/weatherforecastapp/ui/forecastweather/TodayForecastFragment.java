package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.weatherforecastapp.R;


public class TodayForecastFragment extends Fragment {

    private ForeCastWeatherViewModel foreCastWeatherViewModel;
    private String cityName = "Bangalore";
    private String weatherApiKey ="2de26709fcc9817162aa9909b587d145";

    public TodayForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foreCastWeatherViewModel = new ViewModelProvider(this).get(ForeCastWeatherViewModel.class);

    }


    public static TodayForecastFragment newInstance(String param1, String param2) {
        TodayForecastFragment fragment = new TodayForecastFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_forecast, container, false);
    }
}