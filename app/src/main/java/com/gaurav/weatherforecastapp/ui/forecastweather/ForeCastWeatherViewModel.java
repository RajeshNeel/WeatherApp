package com.gaurav.weatherforecastapp.ui.forecastweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForeCastWeatherViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForeCastWeatherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}