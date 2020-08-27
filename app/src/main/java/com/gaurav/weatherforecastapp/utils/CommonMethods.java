package com.gaurav.weatherforecastapp.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.gaurav.weatherforecastapp.R;

import dmax.dialog.SpotsDialog;

/**
 * Created by Gaurav Sharma on 28-08-2020 on 00:06 .
 */
public class CommonMethods {

    public static void showToast(Context context, String message, int duration){
        Toast toast = Toast.makeText(context, message, duration);
        View view = toast.getView();
        view.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(ContextCompat.getColor(context, R.color.white_color));
        toast.show();
    }

    static CharSequence s;
    public static  void foo(CharSequence cs) {

        s = cs;
        System.out.println(cs);
    }

    private static SpotsDialog progressDialogs;
    public static void createProgress(Context context,final String message){

        CharSequence cs = message;
        String st = cs.toString();
        foo(st);

        progressDialogs = new SpotsDialog(context, message);
        progressDialogs.setCancelable(false);
        progressDialogs.setMessage(s);
        progressDialogs.setCanceledOnTouchOutside(false);
        progressDialogs.show();

    }
    public static void closeProgress(){
        if(progressDialogs!=null){
            if(progressDialogs.isShowing()){
                progressDialogs.dismiss();
            }
        }
    }

    public static boolean haveNetworkConnection(Context mContext) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}
