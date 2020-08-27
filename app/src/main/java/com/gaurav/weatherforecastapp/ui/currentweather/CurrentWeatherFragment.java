package com.gaurav.weatherforecastapp.ui.currentweather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.retrofit.response.CurrentWeatherDataResponse;
import com.gaurav.weatherforecastapp.storage.SharedPreference;
import com.gaurav.weatherforecastapp.utils.CommonMethods;
import com.gaurav.weatherforecastapp.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentWeatherFragment extends Fragment {

    private CurrentWeatherViewModel currentWeatherViewModel;
    private NavController navController;


    private String cityName = "Bangalore";
    private String weatherApiKey ="2de26709fcc9817162aa9909b587d145";

    @BindView(R.id.temp) TextView temp;
    @BindView(R.id.speed)  TextView speed;
    @BindView(R.id.humidity) TextView humidity;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        currentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        currentWeatherViewModel = new ViewModelProvider(this).get(CurrentWeatherViewModel.class);




        View currentWeatherView = inflater.inflate(R.layout.fragment_current_weather, container, false);

        ButterKnife.bind(this,currentWeatherView);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cityName);

        if(CommonMethods.haveNetworkConnection(getActivity())) {

            CommonMethods.createProgress(getActivity(), "Loading Weather Data...");

            currentWeatherViewModel.getCurrentWeatherInformation(cityName, weatherApiKey).observe(getViewLifecycleOwner(), new Observer<CurrentWeatherDataResponse>() {
                @Override
                public void onChanged(CurrentWeatherDataResponse currentWeatherDataResponse) {
                    CommonMethods.closeProgress();
                    Log.v(Constants.TAG, "Response:" + new Gson().toJson(currentWeatherDataResponse));


                    try {
                        if (currentWeatherDataResponse == null) {
                            Log.v(Constants.TAG, "weatherInfo is null");
                            return;
                        }
                        if (currentWeatherDataResponse.getError() == null) {

                            Log.v(Constants.TAG, "Response of current Weather :" + new Gson().toJson(currentWeatherDataResponse));

                            SharedPreference.saveWeatherObjectToSharedPreference(getContext(),"CurrentWeatherInfo",currentWeatherDataResponse);

                            List<CurrentWeatherDataResponse.WeatherData> weatherDataList = currentWeatherDataResponse.getWeatherDataList();

                            CurrentWeatherDataResponse.WeatherMainData weatherMainData = currentWeatherDataResponse.getWeatherMainData();

                            CurrentWeatherDataResponse.WeatherWindData weatherWindData = currentWeatherDataResponse.getWindData();

                            CurrentWeatherDataResponse.WeatherSysData weatherSysData = currentWeatherDataResponse.getSysData();

                            String cityName = currentWeatherDataResponse.getCityName();
                            String statusCode = currentWeatherDataResponse.getStatusCod();
                            String timeZone = currentWeatherDataResponse.getTimezone();

                            Log.v(Constants.TAG," City :"+cityName+ "timeZone :"+timeZone+ " temp :"+weatherMainData.getTemp()+" humidity :"+weatherMainData.getHumidity()
                                    +" speed :"+weatherWindData.getWindSpeed());

                        }

                        else {
                            Toast.makeText(getActivity(), "Error is " +currentWeatherDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(Constants.TAG, " Get Current weatherData  " + currentWeatherDataResponse.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }else{
            CommonMethods.showToast(getActivity(),"Please check your internet connection...", Toast.LENGTH_SHORT);

        }


        return currentWeatherView;
    }
}