package com.gaurav.weatherforecastapp.retrofit.response;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 00:23 .
 */
public class CurrentWeatherDataResponse {

    private Throwable error;

    public CurrentWeatherDataResponse(Throwable error) {
        this.error = error;
    }

    @SerializedName("cod")
    @Keep
    @Expose
    private String statusCod;

    @SerializedName("message")
    @Keep
    @Expose
    private String message;

    @SerializedName("timezone")
    @Keep
    @Expose
    private String timezone;

    @SerializedName("name")
    @Keep
    @Expose
    private String cityName;



    @SerializedName("weather")
    @Keep
    @Expose
    private List<WeatherData> weatherDataList;


    @SerializedName("main")
    @Keep
    @Expose
    private WeatherMainData weatherMainData;

    @SerializedName("wind")
    @Keep
    @Expose
    private WeatherWindData windData;

    @SerializedName("sys")
    @Keep
    @Expose
    private WeatherSysData sysData;





    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public String getStatusCod() {
        return statusCod;
    }

    public void setStatusCod(String statusCod) {
        this.statusCod = statusCod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    public WeatherMainData getWeatherMainData() {
        return weatherMainData;
    }

    public void setWeatherMainData(WeatherMainData weatherMainData) {
        this.weatherMainData = weatherMainData;
    }

    public WeatherWindData getWindData() {
        return windData;
    }

    public void setWindData(WeatherWindData windData) {
        this.windData = windData;
    }

    public WeatherSysData getSysData() {
        return sysData;
    }

    public void setSysData(WeatherSysData sysData) {
        this.sysData = sysData;
    }

    public static class WeatherData {

        @SerializedName("main")
        @Keep
        @Expose
        private String main;

        @SerializedName("description")
        @Keep
        @Expose
        private String description;

        @SerializedName("icon")
        @Keep
        @Expose
        private String icon;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }


    public static class WeatherMainData {

        @SerializedName("temp")
        @Keep
        @Expose
        private String temp;

        @SerializedName("temp_min")
        @Keep
        @Expose
        private String minTemp;

        @SerializedName("temp_max")
        @Keep
        @Expose
        private String maxTemp;

        @SerializedName("pressure")
        @Keep
        @Expose
        private String pressure;

        @SerializedName("humidity")
        @Keep
        @Expose
        private String humidity;

        @SerializedName("feels_like")
        @Keep
        @Expose
        private String feels_like;


        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(String minTemp) {
            this.minTemp = minTemp;
        }

        public String getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(String maxTemp) {
            this.maxTemp = maxTemp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(String feels_like) {
            this.feels_like = feels_like;
        }
    }


    public static class WeatherWindData {

        @SerializedName("speed")
        @Keep
        @Expose
        private String windSpeed;

        @SerializedName("deg")
        @Keep
        @Expose
        private String windDeg;

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getWindDeg() {
            return windDeg;
        }

        public void setWindDeg(String windDeg) {
            this.windDeg = windDeg;
        }
    }


    public static class WeatherSysData {

        @SerializedName("country")
        @Keep
        @Expose
        private String countryName;

        @SerializedName("sunrise")
        @Keep
        @Expose
        private String sunriseTime;

        @SerializedName("sunset")
        @Keep
        @Expose
        private String sunsetTime;

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getSunriseTime() {
            return sunriseTime;
        }

        public void setSunriseTime(String sunriseTime) {
            this.sunriseTime = sunriseTime;
        }

        public String getSunsetTime() {
            return sunsetTime;
        }

        public void setSunsetTime(String sunsetTime) {
            this.sunsetTime = sunsetTime;
        }
    }



}




