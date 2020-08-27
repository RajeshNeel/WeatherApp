package com.gaurav.weatherforecastapp.ui.currentweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gaurav.weatherforecastapp.retrofit.repository.WeatherDataRepository;
import com.gaurav.weatherforecastapp.retrofit.response.CurrentWeatherDataResponse;

public class CurrentWeatherViewModel extends AndroidViewModel {

    private WeatherDataRepository weatherDataRepository;

    public CurrentWeatherViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<CurrentWeatherDataResponse> getCurrentWeatherInformation(String cityName, String weatherApiKey) {
        return weatherDataRepository.getCurrentWeatherData(cityName,weatherApiKey);
    }

}