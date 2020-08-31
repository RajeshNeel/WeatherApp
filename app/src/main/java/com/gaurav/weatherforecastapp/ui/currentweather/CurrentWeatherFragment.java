package com.gaurav.weatherforecastapp.ui.currentweather;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.retrofit.response.CurrentWeatherDataResponse;
import com.gaurav.weatherforecastapp.storage.SharedPreference;
import com.gaurav.weatherforecastapp.utils.CommonMethods;
import com.gaurav.weatherforecastapp.utils.Constants;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class CurrentWeatherFragment extends Fragment {

    private CurrentWeatherViewModel currentWeatherViewModel;
    private NavController navController;
    List<Place.Field> fields;
    View navHeaderView;
    NavigationView navigationView;
    TextView textNavLocation;
    CurrentWeatherDataResponse currentWeatherDataResponse  = null;

    @BindView(R.id.textViewCurrentDate) TextView textCurrentDateTime;
    @BindView(R.id.textViewTemperature)  TextView textCurrentTemp;
    @BindView(R.id.textViewFeelsTemp) TextView textFeelsTemp;
    @BindView(R.id.textViewMinTemp) TextView textMinTemp;
    @BindView(R.id.textViewMaxTemp) TextView textMaxTemp;
    @BindView(R.id.textViewWeatherInfo) TextView textWeatherInfoDesc;
    @BindView(R.id.textViewHumidityValue) TextView textHumidity;
    @BindView(R.id.textViewPressureValue) TextView textPressure;
    @BindView(R.id.textViewUvIndexValue) TextView textUvIndex;
    @BindView(R.id.textViewWindValue) TextView textWind;
    @BindView(R.id.textViewSunriseTime) TextView textSunriseTime;
    @BindView(R.id.textViewSunsetTime) TextView textSunsetTime;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mySwipeRefreshLayout;
    @BindView(R.id.imageViewgif) GifImageView gifImageView;



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
        setHasOptionsMenu(true);

        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        navigationView = getActivity().findViewById(R.id.nav_view);

        if(!Places.isInitialized()){
            Places.initialize(getContext(), Constants.googleMapsApiKey);
        }
        PlacesClient placesClient = Places.createClient(getContext());


        navHeaderView =  navigationView.getHeaderView(0);
        textNavLocation = (TextView)navHeaderView.findViewById(R.id.textViewLocation);


        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);

        if(!CommonMethods.haveNetworkConnection(getContext())){


            try {
                currentWeatherDataResponse = SharedPreference.getWeatherSavedObjectFromPreference(getContext(),"CurrentWeatherInfo", CurrentWeatherDataResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(currentWeatherDataResponse!=null ) {
                CommonMethods.showToast(getActivity(), "current weather list size :" + currentWeatherDataResponse.getCityName(), Toast.LENGTH_SHORT);
                updateCurrentUi(currentWeatherDataResponse);
            }
            else{
                CommonMethods.showToast(getActivity(),"data not available :",Toast.LENGTH_SHORT);
            }
        }
        else{
            getWeatherData(Constants.defaultCityName,Constants.weatherApiKey);

        }

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(Constants.TAG, "onRefresh called from SwipeRefreshLayout");

                        getWeatherData(Constants.defaultCityName,Constants.weatherApiKey);
                    }
                }
        );


        return currentWeatherView;
    }

    private void updateCurrentUi(CurrentWeatherDataResponse currentWeatherDataResponse) {


        List<CurrentWeatherDataResponse.WeatherData> weatherDataList = currentWeatherDataResponse.getWeatherDataList();

        CurrentWeatherDataResponse.WeatherMainData weatherMainData = currentWeatherDataResponse.getWeatherMainData();

        CurrentWeatherDataResponse.WeatherWindData weatherWindData = currentWeatherDataResponse.getWindData();

        CurrentWeatherDataResponse.WeatherSysData weatherSysData = currentWeatherDataResponse.getSysData();


        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(currentWeatherDataResponse.getCityName());
        textNavLocation.setText(currentWeatherDataResponse.getCityName());
        Constants.defaultCityName =  currentWeatherDataResponse.getCityName();

        textCurrentDateTime.setText(CommonMethods.convertUnixToDate(Long.parseLong(currentWeatherDataResponse.getCurrentDate())).concat(" "+
                Long.parseLong(currentWeatherDataResponse.getTimezone())));

        int currentTemp = (int)  CommonMethods.convertKelvinToCelsius(Float.parseFloat(weatherMainData.getTemp()));
        int feels_like_temp = (int) CommonMethods.convertKelvinToCelsius(Float.parseFloat(weatherMainData.getFeels_like()));
        int minTemp = (int)CommonMethods.convertKelvinToCelsius(Float.parseFloat(weatherMainData.getMinTemp()));
        int maxTemp = (int) CommonMethods.convertKelvinToCelsius(Float.parseFloat(weatherMainData.getMaxTemp()));

        textCurrentTemp.setText(String.valueOf(currentTemp)+" \u00B0");
        textFeelsTemp.setText("Feels like "+String.valueOf(feels_like_temp).concat(" \u00B0"));
        textMinTemp.setText(String.valueOf(minTemp).concat(" \u00B0"));
        textMaxTemp.setText(String.valueOf(maxTemp).concat(" \u00B0"));
        textWeatherInfoDesc.setText(weatherDataList.get(0).getMain());
        textHumidity.setText(weatherMainData.getHumidity()+"%");
        textPressure.setText(weatherMainData.getPressure()+" mmHg");
        textWind.setText(weatherWindData.getWindSpeed().concat(" "+"Km"+"/"+"h"));
        textSunriseTime.setText(String.valueOf(CommonMethods.convertUnixToHour(Long.parseLong(weatherSysData.getSunriseTime()))));
        textSunsetTime.setText(String.valueOf(CommonMethods.convertUnixToHour(Long.parseLong(weatherSysData.getSunsetTime()))));




    }

    @Override
    public void onResume() {
        super.onResume();
    //    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Constants.defaultCityName);
    }

    private void getWeatherData(String enteredCityName, String weatherApiKey){

       if(CommonMethods.haveNetworkConnection(getActivity())) {

           CommonMethods.createProgress(getActivity(), "Loading Weather Data...");

           currentWeatherViewModel.getCurrentWeatherInformation(enteredCityName,weatherApiKey).observe(getViewLifecycleOwner(), new Observer<CurrentWeatherDataResponse>() {
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

                           updateCurrentUi(currentWeatherDataResponse);

                           Log.v(Constants.TAG," City :"+currentWeatherDataResponse.getCityName()+ "timeZone :"+currentWeatherDataResponse.getTimezone()+ " date :"+currentWeatherDataResponse.getCurrentDate()+" " +
                                   "humidity :"+currentWeatherDataResponse.getWeatherMainData().getHumidity()
                                   +" speed :"+currentWeatherDataResponse.getWindData().getWindSpeed());
                           mySwipeRefreshLayout.setRefreshing(false);

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


   }

    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //getMenuInflater().inflate(R.menu.main, menu);
        inflater.inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Current Weather clicked","search View");

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getContext());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v(Constants.TAG,"typed search text :"+query);
                getWeatherData(query,Constants.weatherApiKey);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(Constants.TAG, "Place: " + place.getName() + ", " + place.getId());
            }
            else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(Constants.TAG, status.getStatusMessage());


            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }





        super.onActivityResult(requestCode, resultCode, data);
    }
}