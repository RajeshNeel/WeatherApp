package com.gaurav.weatherforecastapp.ui.currentweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentWeatherViewModel extends AndroidViewModel {


    public CurrentWeatherViewModel(@NonNull Application application) {
        super(application);
    }
}