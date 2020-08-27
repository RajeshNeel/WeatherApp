package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gaurav.weatherforecastapp.retrofit.repository.WeatherDataRepository;
import com.gaurav.weatherforecastapp.retrofit.response.WeatherForecastDataResponse;

public class ForeCastWeatherViewModel extends AndroidViewModel {

    private WeatherDataRepository weatherDataRepository;

    public ForeCastWeatherViewModel(@NonNull Application application) {
        super(application);
        weatherDataRepository = new WeatherDataRepository(application);
    }

    public LiveData<WeatherForecastDataResponse> getForecastWeatherInformation(String cityName, String weatherApiKey) {
        return weatherDataRepository.getForecastWeatherData(cityName,weatherApiKey);
    }


}