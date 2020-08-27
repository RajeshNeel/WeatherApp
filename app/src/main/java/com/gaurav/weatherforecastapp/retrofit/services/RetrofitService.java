package com.gaurav.weatherforecastapp.retrofit.services;

import android.util.Log;

import com.gaurav.weatherforecastapp.utils.Constants;
import com.gaurav.weatherforecastapp.utils.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 00:09 .
 */
public class RetrofitService {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.v(Constants.TAG,"Logger for Retrofit:"+message);
                if(message.contains("false") || message.contains("operation failed")){
                    Log.v(Constants.TAG,"Coming here 1:"+message);

                }

            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)

                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();


        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static <S> S createService(Class<S> serviceClass) {
        retrofit = getClient();

        return retrofit.create(serviceClass);
    }

}
