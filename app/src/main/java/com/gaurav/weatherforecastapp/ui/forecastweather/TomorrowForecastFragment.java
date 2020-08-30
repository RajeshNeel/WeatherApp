package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.retrofit.response.WeatherForecastDataResponse;
import com.gaurav.weatherforecastapp.storage.SharedPreference;
import com.gaurav.weatherforecastapp.utils.CommonMethods;
import com.gaurav.weatherforecastapp.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TomorrowForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TomorrowForecastFragment extends Fragment {

    private ForeCastWeatherViewModel foreCastWeatherViewModel;
    private String cityName = "Bangalore";
    private String weatherApiKey ="2de26709fcc9817162aa9909b587d145";

    @BindView(R.id.textViewCurrentDateForecast) TextView textCurrentDateTime;
    @BindView(R.id.textViewTemperatureForecast)  TextView textCurrentTemp;
    @BindView(R.id.textViewFeelsTempForecast) TextView textFeelsTemp;
    @BindView(R.id.textViewMinTempForecast) TextView textMinTemp;
    @BindView(R.id.textViewMaxTempForecast) TextView textMaxTemp;
    @BindView(R.id.textViewWeatherInfoForecast) TextView textWeatherInfoDesc;
    @BindView(R.id.textViewHumidityValueForecast) TextView textHumidity;
    @BindView(R.id.textViewPressureValueForecast) TextView textPressure;
    @BindView(R.id.textViewUvIndexValueForecast) TextView textUvIndex;
    @BindView(R.id.textViewWindValueForecast) TextView textWind;
    @BindView(R.id.textViewSunriseTimeForecast) TextView textSunriseTime;
    @BindView(R.id.textViewSunsetTimeForecast) TextView textSunsetTime;


    public TomorrowForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foreCastWeatherViewModel = new ViewModelProvider(this).get(ForeCastWeatherViewModel.class);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        foreCastWeatherViewModel = new ViewModelProvider(this).get(ForeCastWeatherViewModel.class);

        View tomorrowForecastView = inflater.inflate(R.layout.fragment_tomorrow_forecast, container, false);

        ButterKnife.bind(this,tomorrowForecastView);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cityName);

        WeatherForecastDataResponse.WeatherForecastCityInfo weatherForecastCityInfo = null;
        try {
            WeatherForecastDataResponse weatherForecastDataResponse =  SharedPreference.getWeatherSavedObjectFromPreference(getContext(),"WeatherForecastInfo", WeatherForecastDataResponse.class);

            List<WeatherForecastDataResponse.WeatherForecastDataInfo> weatherForecastDataInfoList = weatherForecastDataResponse.getWeatherForecastDataList();

            weatherForecastCityInfo = weatherForecastDataResponse.getWeatherForecastCityInfo();

            Log.v(Constants.TAG," forecast Tomorrow weather list size :"+weatherForecastDataInfoList.size());

            int currentPosition=0;
            for (WeatherForecastDataResponse.WeatherForecastDataInfo weatherForecastDataInfo : weatherForecastDataInfoList) {

                currentPosition++;
                Log.v(Constants.TAG," forecast date :"+weatherForecastDataInfo.getForecastDate()+" forecast dt :"+ weatherForecastDataInfo.getForecastDt());

                WeatherForecastDataResponse.ForeCastWeatherMainData foreCastWeatherMainData = weatherForecastDataInfo.getForeCastWeatherMainData();

                for(WeatherForecastDataResponse.ForecastWeatherData forecastWeatherData : weatherForecastDataInfo.getForecastWeatherDataList()){

                    Log.v(Constants.TAG," forecast weather data :"+" main :"+forecastWeatherData.getMain()+ " desc :"+forecastWeatherData.getDescription());

                    textWeatherInfoDesc.setText(forecastWeatherData.getMain());

                }

                WeatherForecastDataResponse.ForeCastWeatherCloudData foreCastWeatherCloudData = weatherForecastDataInfo.getForeCastWeatherCloudData();

                WeatherForecastDataResponse.ForecastWeatherWindData forecastWeatherWindData = weatherForecastDataInfo.getForecastWeatherWindData();

                Log.v(Constants.TAG,"cityName :"+ weatherForecastCityInfo.getForecastCityName()+" country :"+weatherForecastCityInfo.getForecastCountryName()
                        +"mainData "+" temp :"+foreCastWeatherMainData.getTemp()+ "humidity :"+foreCastWeatherMainData.getHumidity()+"" +
                        "pressure :"+foreCastWeatherMainData.getPressure());

                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(weatherForecastCityInfo.getForecastCityName());


                if(currentPosition==7){

                    textCurrentDateTime.setText(weatherForecastDataInfoList.get(0).getForecastDate());

                    float currntTemp = CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getTemp()));
                    int currentTemp = (int) currntTemp;
                    float feelsTemp = CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getTemp_kf()));
                    int feels_like_temp = (int) feelsTemp;
                    float minTmp = CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getMinTemp()));
                    int minTemp = (int)minTmp;
                    float maxTmp = CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getMaxTemp()));
                    int maxTemp = (int)maxTmp;


                    textCurrentTemp.setText(currentTemp+" \u00B0");
                    textFeelsTemp.setText("feels like "+String.valueOf(feels_like_temp).concat(" \u00B0"));
                    textMinTemp.setText(String.valueOf(minTemp).concat(" \u00B0"));
                    textMaxTemp.setText(String.valueOf(maxTemp).concat(" \u00B0"));

                    textHumidity.setText(foreCastWeatherMainData.getHumidity()+"%");
                    textPressure.setText(foreCastWeatherMainData.getPressure()+" mmHg");
                    textWind.setText(forecastWeatherWindData.getWindSpeed().concat(" "+"Km"+"/"+"h"));
                }
                WeatherForecastDataResponse.ForecastWeatherCoordinate forecastWeatherCoordinate = weatherForecastCityInfo.getForecastWeatherCoordinate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return tomorrowForecastView;


    }
}