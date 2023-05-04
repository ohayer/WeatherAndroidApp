package com.ohayer.weatherapp.running;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;
import com.ohayer.weatherapp.connect.JsonInfo;

public class Async extends AsyncTask<Void, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private final TextView textView;
    private String city;

    public Async(TextView textView,String city) {
        this.textView = textView;
        this.city = city;
    }


    @Override
    protected String doInBackground(Void... params) {
        JsonInfo jsonInfo = new JsonInfo(city);
        return jsonInfo.getCityName();


    }


    @Override
    protected void onPostExecute(String result) {
        textView.setText("Temperature in "+ city +  " is: " + result + "°C");
    }
}
