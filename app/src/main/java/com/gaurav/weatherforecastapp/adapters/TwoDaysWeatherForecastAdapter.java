package com.gaurav.weatherforecastapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.models.WeatherForecastData;

import java.util.List;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 01:44 .
 */
public class TwoDaysWeatherForecastAdapter extends RecyclerView.Adapter<TwoDaysWeatherForecastAdapter.TwoDaysForecastViewHolder> {

    public List<WeatherForecastData> weatherForecastDataList;
    private Context context;

    public TwoDaysWeatherForecastAdapter(Context mContext,List<WeatherForecastData> weatherForecastDataList) {
        this.weatherForecastDataList = weatherForecastDataList;
        this.context = mContext;

    }


    @NonNull
    @Override
    public TwoDaysForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View forecastDataView = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_days_forecast_data_holder, parent, false);
        return new TwoDaysForecastViewHolder(forecastDataView);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoDaysForecastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return weatherForecastDataList.size();
    }

    public static class TwoDaysForecastViewHolder extends RecyclerView.ViewHolder {
        TextView forecastDate, forecastTemp,forecastWeatherDesc;

        public TwoDaysForecastViewHolder(@NonNull View itemView) {
            super(itemView);

            forecastDate = itemView.findViewById(R.id.textViewDate);
            forecastTemp = itemView.findViewById(R.id.textViewForecastTemp);
            forecastWeatherDesc = itemView.findViewById(R.id.textViewWeatherDesc);
        }
    }
}
