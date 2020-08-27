package com.gaurav.weatherforecastapp.ui.forecastweather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.adapters.TwoDaysWeatherForecastAdapter;
import com.gaurav.weatherforecastapp.models.WeatherForecastData;
import com.gaurav.weatherforecastapp.retrofit.response.WeatherForecastDataResponse;
import com.gaurav.weatherforecastapp.storage.SharedPreference;
import com.gaurav.weatherforecastapp.utils.CommonMethods;
import com.gaurav.weatherforecastapp.utils.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoDaysForecastFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoDaysForecastFragment extends Fragment {


    @BindView(R.id.recyclerViewTwoDaysForecastData) RecyclerView recyclerViewTwoDaysForecastData;
    private ForeCastWeatherViewModel foreCastWeatherViewModel;
    private String cityName = "Bangalore";
    private String weatherApiKey ="2de26709fcc9817162aa9909b587d145";

    TwoDaysWeatherForecastAdapter twoDaysWeatherForecastAdapter;
    public List<WeatherForecastData> weatherForecastDataList;


    public TwoDaysForecastFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        foreCastWeatherViewModel = new ViewModelProvider(this).get(ForeCastWeatherViewModel.class);

    }

    public static TwoDaysForecastFragment newInstance(String param1, String param2) {
        TwoDaysForecastFragment fragment = new TwoDaysForecastFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        foreCastWeatherViewModel = new ViewModelProvider(this).get(ForeCastWeatherViewModel.class);

        View twoDaysForecastView = inflater.inflate(R.layout.fragment_two_days_forecast, container, false);

        ButterKnife.bind(this,twoDaysForecastView);

        weatherForecastDataList = new ArrayList<>();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTwoDaysForecastData.setLayoutManager(linearLayoutManager);
        recyclerViewTwoDaysForecastData.setHasFixedSize(true);

        twoDaysWeatherForecastAdapter = new TwoDaysWeatherForecastAdapter(getActivity(), weatherForecastDataList);
        recyclerViewTwoDaysForecastData.setAdapter(twoDaysWeatherForecastAdapter);
        twoDaysWeatherForecastAdapter.notifyDataSetChanged();



        if(CommonMethods.haveNetworkConnection(getActivity())) {

            CommonMethods.createProgress(getActivity(), "Loading Forecast weather...");

            foreCastWeatherViewModel.getForecastWeatherInformation(cityName,weatherApiKey).observe(getViewLifecycleOwner(), new Observer<WeatherForecastDataResponse>() {
                @Override
                public void onChanged(WeatherForecastDataResponse weatherForecastDataResponse) {
                    CommonMethods.closeProgress();

                    try {
                        if (weatherForecastDataResponse == null) {
                            Log.v(Constants.TAG, "weatherForecast Data is null");
                            return;
                        }
                        if (weatherForecastDataResponse.getError() == null) {

                            Log.v(Constants.TAG, "Response of Forecast Weather :" + new Gson().toJson(weatherForecastDataResponse));

                            SharedPreference.saveWeatherObjectToSharedPreference(getContext(),"WeatherForecastInfo",weatherForecastDataResponse);


                            List<WeatherForecastDataResponse.WeatherForecastDataInfo> weatherForecastDataInfoList = weatherForecastDataResponse.getWeatherForecastDataList();

                            WeatherForecastDataResponse.WeatherForecastCityInfo weatherForecastCityInfo = weatherForecastDataResponse.getWeatherForecastCityInfo();

                            Log.v(Constants.TAG,"forecast weather list size:"+weatherForecastDataInfoList.size());

                            for (WeatherForecastDataResponse.WeatherForecastDataInfo weatherForecastDataInfo : weatherForecastDataInfoList) {

                                Log.v(Constants.TAG,"forecast date :"+weatherForecastDataInfo.getForecastDate()+" forecast dt :"+ weatherForecastDataInfo.getForecastDt());

                                WeatherForecastDataResponse.ForeCastWeatherMainData foreCastWeatherMainData = weatherForecastDataInfo.getForeCastWeatherMainData();

                                for(WeatherForecastDataResponse.ForecastWeatherData forecastWeatherData : weatherForecastDataInfo.getForecastWeatherDataList()){

                                    Log.v(Constants.TAG,"forecast weather data :"+"main :"+forecastWeatherData.getMain()+ "desc :"+forecastWeatherData.getDescription());


                                }

                                WeatherForecastDataResponse.ForeCastWeatherCloudData foreCastWeatherCloudData = weatherForecastDataInfo.getForeCastWeatherCloudData();

                                WeatherForecastDataResponse.ForecastWeatherWindData forecastWeatherWindData = weatherForecastDataInfo.getForecastWeatherWindData();

                                recyclerViewTwoDaysForecastData.setAdapter(twoDaysWeatherForecastAdapter);
                                twoDaysWeatherForecastAdapter.notifyDataSetChanged();

                                Log.v(Constants.TAG,"cityName :"+ weatherForecastCityInfo.getForecastCityName()+" country :"+weatherForecastCityInfo.getForecastCountryName()
                                        +"mainData "+" temp :"+foreCastWeatherMainData.getTemp()+ "humidity :"+foreCastWeatherMainData.getHumidity()+"" +

                                        "pressure :"+foreCastWeatherMainData.getPressure());


                            }

                            WeatherForecastDataResponse.ForecastWeatherCoordinate forecastWeatherCoordinate = weatherForecastCityInfo.getForecastWeatherCoordinate();

                        }
                        else {
                            Toast.makeText(getActivity(), "Error is " +weatherForecastDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(Constants.TAG, " Get Current weatherData  " + weatherForecastDataResponse.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        }else{
            CommonMethods.showToast(getActivity(),"Please check your internet connection...", Toast.LENGTH_SHORT);

        }



        return twoDaysForecastView;
    }
}