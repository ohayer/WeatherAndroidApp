package com.ohayer.weatherapp.running;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ohayer.weatherapp.R;
import com.ohayer.weatherapp.connect.JsonInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Async extends AsyncTask<Void, Void, List<String>> {
    @SuppressLint("StaticFieldLeak")
    private final TextView cityName, wind, temperature, pressure, sunRise, sunSet, dateTime;
    @SuppressLint("StaticFieldLeak")
    private final ImageView conditionImg;

    private final String city;

    @SuppressLint("StaticFieldLeak")
    private final ConstraintLayout constraintLayout;

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public Async(TextView textView, TextView wind, TextView temperature, TextView pressure, TextView sunRise, TextView sunSet, TextView dateTime, ImageView conditionImg, String city, ConstraintLayout constraintLayout, Context context) {
        this.cityName = textView;
        this.wind = wind;
        this.temperature = temperature;
        this.pressure = pressure;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.dateTime = dateTime;
        this.conditionImg = conditionImg;
        this.city = city;
        this.constraintLayout = constraintLayout;
        this.context = context;
    }


    public List<String> weatherProperties = new ArrayList<>();

    @Override
    protected List<String> doInBackground(Void... params) {
        JsonInfo jsonInfo = new JsonInfo(city);
        try {
            weatherProperties.add(jsonInfo.getCityName());
            weatherProperties.add(jsonInfo.getWindSpeed());
            weatherProperties.add(String.valueOf(jsonInfo.getTemperature()));
            weatherProperties.add(jsonInfo.getPressure());
            weatherProperties.add(jsonInfo.getSunrise());
            weatherProperties.add(jsonInfo.getSunset());
            weatherProperties.add(jsonInfo.getWeatherConditions());
            weatherProperties.add(String.valueOf(jsonInfo.DateTime()));
        } catch (NullPointerException e) {
            System.err.println("City does not found " + e.getMessage());
        }
        return weatherProperties;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPostExecute(List<String> weatherProperties) {

        if (weatherProperties.isEmpty()) {
            Toast.makeText(this.context, "City does not found.", Toast.LENGTH_LONG).show();
        }
            try {
            this.cityName.setText(weatherProperties.get(0));
            this.wind.setText(weatherProperties.get(1));
            this.temperature.setText(weatherProperties.get(2));
            this.pressure.setText(weatherProperties.get(3));
            this.sunRise.setText(weatherProperties.get(4));
            this.sunSet.setText(weatherProperties.get(5));
            String weatherConditions = weatherProperties.get(6);
            if (weatherConditions.contains("clear")) {
                this.conditionImg.setImageResource(R.drawable.sun);
            }
            if (weatherConditions.contains("rain")) {
                this.conditionImg.setImageResource(R.drawable.rain);
            }
            if (weatherConditions.contains("snow")) {
                this.conditionImg.setImageResource(R.drawable.snow);
            }
            if (weatherConditions.contains("clouds")) {
                this.conditionImg.setImageResource(R.drawable.cloudy);
            }
            if (weatherConditions.contains("haze")) {
                this.conditionImg.setImageResource(R.drawable.foggy);
            }
            if (weatherConditions.contains("storm")) {
                this.conditionImg.setImageResource(R.drawable.thunder);
            }
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            Date date = inputFormat.parse(weatherProperties.get(7));

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy HH:mm", Locale.US);
            String formattedDate = outputFormat.format(date);
            this.dateTime.setText(formattedDate);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
            String timeCurrent = timeFormat.format(date);

            LocalTime sunrise = LocalTime.parse(weatherProperties.get(4));
            LocalTime sunset = LocalTime.parse(weatherProperties.get(5));
            LocalTime current = LocalTime.parse(timeCurrent);

            if (current.isBefore(sunrise) && current.isAfter(sunset)) {
                this.constraintLayout.setBackgroundResource(R.drawable.backgrounddark);
            }


        } catch (IndexOutOfBoundsException | ParseException e) {
            e.getMessage();
        }
    }
}
