package com.ohayer.weatherapp.connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonInfo extends ApiConnect {

    private JSONObject jsonObject;
    private String city;

    public JsonInfo(String city) {
        this.city = city;
    }

    public String getTemperature() {
        jsonObject = getWeatherByCity(city);
        JSONObject main;
        try {
            main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("temp");
            return (int) Math.round(temperature) + "°C";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPressure() {
        jsonObject = getWeatherByCity(city);
        JSONObject main;
        try {
            main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("pressure");
            return temperature + " hPa";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSunrise() {
        jsonObject = getWeatherByCity(city);
        try {
            long sunriseTimestamp = jsonObject.getJSONObject("sys").getLong("sunrise");
            Date sunriseDate = new Date(sunriseTimestamp * 1000);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String sunriseTime = format.format(sunriseDate);
            return sunriseTime;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSunset() {
        jsonObject = getWeatherByCity(city);
        try {
            long sunsetTimestamp = jsonObject.getJSONObject("sys").getLong("sunset");
            Date sunset = new Date(sunsetTimestamp * 1000);
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            String sunSetTime = format.format(sunset);
            return sunSetTime;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWindSpeed() {
        jsonObject = getWeatherByCity(city);
        try {
            long wind = jsonObject.getJSONObject("wind").getLong("speed");
            return wind + " km/h";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCityName() {
        jsonObject = getWeatherByCity(city);
        try {
            String cityName = jsonObject.getString("name");
            return cityName;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public String getWeatherConditions() {
        jsonObject = getWeatherByCity(city);
        try {
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weatherObject = weatherArray.getJSONObject(0);
            String weather = weatherObject.getString("main");
            return weather.toLowerCase();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}