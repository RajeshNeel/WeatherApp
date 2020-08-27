package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaurav.weatherforecastapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoDaysForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoDaysForecastFragment extends Fragment {



    public TwoDaysForecastFragment() {
        // Required empty public constructor
    }


    public static TwoDaysForecastFragment newInstance(String param1, String param2) {
        TwoDaysForecastFragment fragment = new TwoDaysForecastFragment();
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
        return inflater.inflate(R.layout.fragment_two_days_forecast, container, false);
    }
}