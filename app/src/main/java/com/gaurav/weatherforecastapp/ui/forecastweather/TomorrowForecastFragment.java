package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.weatherforecastapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TomorrowForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TomorrowForecastFragment extends Fragment {



    public TomorrowForecastFragment() {
        // Required empty public constructor
    }


    public static TomorrowForecastFragment newInstance(String param1, String param2) {
        TomorrowForecastFragment fragment = new TomorrowForecastFragment();
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
        return inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false);
    }
}