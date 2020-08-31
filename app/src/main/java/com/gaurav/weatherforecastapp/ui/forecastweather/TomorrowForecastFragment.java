package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    WeatherForecastDataResponse.WeatherForecastCityInfo weatherForecastCityInfo = null;
    WeatherForecastDataResponse weatherForecastDataResponse  = null;


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
        setHasOptionsMenu(true);


        if(!CommonMethods.haveNetworkConnection(getContext())){


            try {
                weatherForecastDataResponse = SharedPreference.getWeatherSavedObjectFromPreference(getContext(),"WeatherForecastInfo", WeatherForecastDataResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(weatherForecastDataResponse!=null ) {
                CommonMethods.showToast(getActivity(), "current weather list size :" + weatherForecastDataResponse.getWeatherForecastCityInfo().getForecastCityName(), Toast.LENGTH_SHORT);
                updateCurrentUi(weatherForecastDataResponse);
            }
            else{
                CommonMethods.showToast(getActivity(),"data not available :",Toast.LENGTH_SHORT);
            }
        }
        else{
            updateCurrentUi(weatherForecastDataResponse);

        }



        return tomorrowForecastView;


    }

    private void updateCurrentUi(WeatherForecastDataResponse weatherForecastDataResponse) {

        try {

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

                    int currentTemp = (int) CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getTemp()));
                    int feels_like_temp = (int) CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getTemp_kf()));
                    int minTemp = (int)CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getMinTemp()));
                    int maxTemp = (int)CommonMethods.convertKelvinToCelsius(Float.parseFloat(foreCastWeatherMainData.getMaxTemp()));


                    textCurrentTemp.setText(currentTemp+" \u00B0");
                    textFeelsTemp.setText("Feels like "+String.valueOf(feels_like_temp).concat(" \u00B0"));
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




    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //getMenuInflater().inflate(R.menu.main, menu);
        inflater.inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);

        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Current Weather clicked","search View");


            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v(Constants.TAG,"typed search text :"+query);
                searchView.onActionViewCollapsed();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.v(Constants.TAG,"typed search text char :"+newText);

                return true;
            }
        });
    }
}