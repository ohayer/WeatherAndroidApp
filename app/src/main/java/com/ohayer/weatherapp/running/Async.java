package com.ohayer.weatherapp.running;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;
import com.ohayer.weatherapp.connect.JsonInfo;

import java.util.ArrayList;
import java.util.List;

public class Async extends AsyncTask<Void, Void, List<String>> {
    @SuppressLint("StaticFieldLeak")
    private final TextView cityName;
    @SuppressLint("StaticFieldLeak")
    private final TextView wind;
    @SuppressLint("StaticFieldLeak")
    private final TextView temperature;
    @SuppressLint("StaticFieldLeak")
    private final TextView pressure;
    @SuppressLint("StaticFieldLeak")
    private final TextView sunRise;
    @SuppressLint("StaticFieldLeak")
    private final TextView sunSet;
    private String city;

    public Async(TextView textView, TextView wind, TextView temperature, TextView pressure, TextView sunRise, TextView sunSet, String city) {
        this.cityName = textView;
        this.wind = wind;
        this.temperature = temperature;
        this.pressure = pressure;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.city = city;
    }


    @Override
    protected List<String> doInBackground(Void... params) {
        JsonInfo jsonInfo = new JsonInfo(city);
        List<String> weatherProperties = new ArrayList<>();
        if (jsonInfo != null) {
            weatherProperties.add(jsonInfo.getCityName());
            weatherProperties.add(jsonInfo.getWindSpeed());
            weatherProperties.add(String.valueOf(jsonInfo.getTemperature()));
            weatherProperties.add(jsonInfo.getPressure());
            weatherProperties.add(jsonInfo.getSunrise());
            weatherProperties.add(jsonInfo.getSunset());
        }
        return weatherProperties;


    }


    @Override
    protected void onPostExecute(List<String> weatherProperties) {
        this.cityName.setText(weatherProperties.get(0));
        this.wind.setText(weatherProperties.get(1));
        this.temperature.setText(weatherProperties.get(2));
        this.pressure.setText(weatherProperties.get(3));
        this.sunRise.setText(weatherProperties.get(4));
        this.sunSet.setText(weatherProperties.get(5));
    }
}
