package com.gaurav.weatherforecastapp.models;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 01:52 .
 */
public class WeatherForecastData {

    String forecastDate,forecastTemp,forecastWeatherDesc;

    public WeatherForecastData(String forecastDate, String forecastTemp, String forecastWeatherDesc) {
        this.forecastDate = forecastDate;
        this.forecastTemp = forecastTemp;
        this.forecastWeatherDesc = forecastWeatherDesc;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastTemp() {
        return forecastTemp;
    }

    public void setForecastTemp(String forecastTemp) {
        this.forecastTemp = forecastTemp;
    }

    public String getForecastWeatherDesc() {
        return forecastWeatherDesc;
    }

    public void setForecastWeatherDesc(String forecastWeatherDesc) {
        this.forecastWeatherDesc = forecastWeatherDesc;
    }
}
