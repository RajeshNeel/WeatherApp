package com.gaurav.weatherforecastapp.interfaces;

import com.gaurav.weatherforecastapp.retrofit.response.CurrentWeatherDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 00:19 .
 */
public interface RetrofitApi {

    @GET("weather")
    Call<CurrentWeatherDataResponse> getCurrentWeatherData(@Query("q") String cityName, @Query("appid") String weatherApiKey);

}
