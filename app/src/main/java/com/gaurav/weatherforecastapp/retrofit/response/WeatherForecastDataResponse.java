package com.gaurav.weatherforecastapp.retrofit.response;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 02:49 AM.
 */
public class WeatherForecastDataResponse {

    private Throwable error;

    public WeatherForecastDataResponse(Throwable error) {
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

    @SerializedName("list")
    @Keep
    @Expose
    private List<WeatherForecastDataInfo> weatherForecastDataList;

    @SerializedName("city")
    @Keep
    @Expose
    private WeatherForecastCityInfo weatherForecastCityInfo;


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

    public List<WeatherForecastDataInfo> getWeatherForecastDataList() {
        return weatherForecastDataList;
    }

    public void setWeatherForecastDataList(List<WeatherForecastDataInfo> weatherForecastDataList) {
        this.weatherForecastDataList = weatherForecastDataList;
    }

    public WeatherForecastCityInfo getWeatherForecastCityInfo() {
        return weatherForecastCityInfo;
    }

    public void setWeatherForecastCityInfo(WeatherForecastCityInfo weatherForecastCityInfo) {
        this.weatherForecastCityInfo = weatherForecastCityInfo;
    }

    public static class WeatherForecastDataInfo {

        @SerializedName("main")
        @Keep
        @Expose
        private ForeCastWeatherMainData foreCastWeatherMainData;

        @SerializedName("weather")
        @Keep
        @Expose
        private List<ForecastWeatherData> forecastWeatherDataList;

        @SerializedName("clouds")
        @Keep
        @Expose
        private ForeCastWeatherCloudData foreCastWeatherCloudData;

        @SerializedName("wind")
        @Keep
        @Expose
        private ForecastWeatherWindData forecastWeatherWindData;

        @SerializedName("dt_txt")
        @Keep
        @Expose
        private String forecastDate;

        @SerializedName("dt")
        @Keep
        @Expose
        private String forecastDt;


        public ForeCastWeatherMainData getForeCastWeatherMainData() {
            return foreCastWeatherMainData;
        }

        public void setForeCastWeatherMainData(ForeCastWeatherMainData foreCastWeatherMainData) {
            this.foreCastWeatherMainData = foreCastWeatherMainData;
        }

        public List<ForecastWeatherData> getForecastWeatherDataList() {
            return forecastWeatherDataList;
        }

        public void setForecastWeatherDataList(List<ForecastWeatherData> forecastWeatherDataList) {
            this.forecastWeatherDataList = forecastWeatherDataList;
        }

        public ForeCastWeatherCloudData getForeCastWeatherCloudData() {
            return foreCastWeatherCloudData;
        }

        public void setForeCastWeatherCloudData(ForeCastWeatherCloudData foreCastWeatherCloudData) {
            this.foreCastWeatherCloudData = foreCastWeatherCloudData;
        }

        public ForecastWeatherWindData getForecastWeatherWindData() {
            return forecastWeatherWindData;
        }

        public void setForecastWeatherWindData(ForecastWeatherWindData forecastWeatherWindData) {
            this.forecastWeatherWindData = forecastWeatherWindData;
        }

        public String getForecastDate() {
            return forecastDate;
        }

        public void setForecastDate(String forecastDate) {
            this.forecastDate = forecastDate;
        }

        public String getForecastDt() {
            return forecastDt;
        }

        public void setForecastDt(String forecastDt) {
            this.forecastDt = forecastDt;
        }
    }

    public static class WeatherForecastCityInfo {

        @SerializedName("name")
        @Keep
        @Expose
        private String forecastCityName;

        @SerializedName("country")
        @Keep
        @Expose
        private String forecastCountryName;

        @SerializedName("coord")
        @Keep
        @Expose
        private ForecastWeatherCoordinate forecastWeatherCoordinate;


        public String getForecastCityName() {
            return forecastCityName;
        }

        public void setForecastCityName(String forecastCityName) {
            this.forecastCityName = forecastCityName;
        }

        public String getForecastCountryName() {
            return forecastCountryName;
        }

        public void setForecastCountryName(String forecastCountryName) {
            this.forecastCountryName = forecastCountryName;
        }

        public ForecastWeatherCoordinate getForecastWeatherCoordinate() {
            return forecastWeatherCoordinate;
        }

        public void setForecastWeatherCoordinate(ForecastWeatherCoordinate forecastWeatherCoordinate) {
            this.forecastWeatherCoordinate = forecastWeatherCoordinate;
        }
    }

    public static class ForeCastWeatherMainData {

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

        @SerializedName("sea_level")
        @Keep
        @Expose
        private String sea_level;

        @SerializedName("grnd_level")
        @Keep
        @Expose
        private String ground_level;

        @SerializedName("temp_kf")
        @Keep
        @Expose
        private String temp_kf;

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

        public String getSea_level() {
            return sea_level;
        }

        public void setSea_level(String sea_level) {
            this.sea_level = sea_level;
        }

        public String getGround_level() {
            return ground_level;
        }

        public void setGround_level(String ground_level) {
            this.ground_level = ground_level;
        }

        public String getTemp_kf() {
            return temp_kf;
        }

        public void setTemp_kf(String temp_kf) {
            this.temp_kf = temp_kf;
        }
    }

    public static class ForecastWeatherData {

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

    public static class ForeCastWeatherCloudData {

        @SerializedName("all")
        @Keep
        @Expose
        private String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public static class ForecastWeatherWindData {

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

    public static class ForecastWeatherCoordinate {

        @SerializedName("lat")
        @Keep
        @Expose
        private String forecastLatitude;

        @SerializedName("lon")
        @Keep
        @Expose
        private String forecastLongitude;


        public String getForecastLatitude() {
            return forecastLatitude;
        }

        public void setForecastLatitude(String forecastLatitude) {
            this.forecastLatitude = forecastLatitude;
        }

        public String getForecastLongitude() {
            return forecastLongitude;
        }

        public void setForecastLongitude(String forecastLongitude) {
            this.forecastLongitude = forecastLongitude;
        }
    }

}
