package com.gaurav.weatherforecastapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gaurav.weatherforecastapp.R;
import com.gaurav.weatherforecastapp.models.WeatherForecastData;
import com.gaurav.weatherforecastapp.utils.CommonMethods;

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

        int positions =28;

        

        holder.forecastDate.setText(weatherForecastDataList.get(position).getForecastDate());

        int currentTemp = (int)  CommonMethods.convertKelvinToCelsius(Float.parseFloat(weatherForecastDataList.get(position).getForecastTemp()));


        holder.forecastTemp.setText(String.valueOf(currentTemp)+ " \u00B0");


        holder.forecastWeatherDesc.setText(weatherForecastDataList.get(position).getForecastWeatherDesc());

        if(weatherForecastDataList.get(position).getForecastWeatherDesc().equalsIgnoreCase("Clouds")){

            holder.forecastImageView.setImageResource(R.drawable.moving_cloud_gif);

        }

        else if(weatherForecastDataList.get(position).getForecastWeatherDesc().equalsIgnoreCase("Rain")){
            holder.forecastImageView.setImageResource(R.drawable.rain_gif);
        }
        else{
            holder.forecastImageView.setImageResource(R.drawable.sun_animated_img);
        }

    }

    @Override
    public int getItemCount() {
        return weatherForecastDataList.size();
    }

    public static class TwoDaysForecastViewHolder extends RecyclerView.ViewHolder {
        TextView forecastDate, forecastTemp,forecastWeatherDesc;
        ImageView forecastImageView;

        public TwoDaysForecastViewHolder(@NonNull View itemView) {
            super(itemView);

            forecastDate = itemView.findViewById(R.id.textViewDate);
            forecastTemp = itemView.findViewById(R.id.textViewForecastTemp);
            forecastWeatherDesc = itemView.findViewById(R.id.textViewWeatherDesc);
            forecastImageView = itemView.findViewById(R.id.imageViewWeatherImage);
        }
    }
}
