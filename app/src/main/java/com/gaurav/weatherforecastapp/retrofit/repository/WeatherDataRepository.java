package com.gaurav.weatherforecastapp.retrofit.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gaurav.weatherforecastapp.interfaces.RetrofitApi;
import com.gaurav.weatherforecastapp.retrofit.response.CurrentWeatherDataResponse;
import com.gaurav.weatherforecastapp.retrofit.response.WeatherForecastDataResponse;
import com.gaurav.weatherforecastapp.retrofit.services.RetrofitService;
import com.gaurav.weatherforecastapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 00:27 .
 */
public class WeatherDataRepository {

    private static WeatherDataRepository weatherDataRepository;
    private RetrofitApi retrofitApi;

    private static WeatherDataRepository getInstance(){
        if(weatherDataRepository == null){
            weatherDataRepository = new WeatherDataRepository();
        }
        return weatherDataRepository;
    }


    public WeatherDataRepository(Application application){

        retrofitApi = RetrofitService.createService(RetrofitApi.class);
    }

    public WeatherDataRepository(){
        retrofitApi = RetrofitService.createService(RetrofitApi.class);
    }

    public LiveData<CurrentWeatherDataResponse> getCurrentWeatherData(String cityName, String weatherApiKey){

        final MutableLiveData<CurrentWeatherDataResponse> weatherDataResponseMutableLiveData = new MutableLiveData<>();

        retrofitApi.getCurrentWeatherData(cityName,weatherApiKey).enqueue(new Callback<CurrentWeatherDataResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherDataResponse> call, Response<CurrentWeatherDataResponse> response) {

                if (response.isSuccessful()) {
                    Log.v(Constants.TAG, "Result Current Weather Data Repository:" + response.message());

                    weatherDataResponseMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<CurrentWeatherDataResponse> call, Throwable t) {

                weatherDataResponseMutableLiveData.setValue(new CurrentWeatherDataResponse(t));
            }
        });

        return weatherDataResponseMutableLiveData;

    }

    public LiveData<WeatherForecastDataResponse> getForecastWeatherData(String cityName, String weatherApiKey){

        final MutableLiveData<WeatherForecastDataResponse> weatherForecastDataResponseMutableLiveData = new MutableLiveData<>();

        retrofitApi.getWeatherForecastData(cityName,weatherApiKey).enqueue(new Callback<WeatherForecastDataResponse>() {
            @Override
            public void onResponse(Call<WeatherForecastDataResponse> call, Response<WeatherForecastDataResponse> weatherForecastDataResponseResponse) {

                if (weatherForecastDataResponseResponse.isSuccessful()) {
                    Log.v(Constants.TAG, "Result Forecast Weather Data Repository:" + weatherForecastDataResponseResponse.message());

                    weatherForecastDataResponseMutableLiveData.setValue(weatherForecastDataResponseResponse.body());
                }


            }

            @Override
            public void onFailure(Call<WeatherForecastDataResponse> call, Throwable t) {

                weatherForecastDataResponseMutableLiveData.setValue(new WeatherForecastDataResponse(t));

            }
        });

        return weatherForecastDataResponseMutableLiveData;
    }


}
