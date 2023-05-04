package com.ohayer.weatherapp.connect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class JsonInfo extends ApiConnect {

    private JSONObject jsonObject;
    private String city;

    public JsonInfo(String city) {
        this.city = city;
    }

    public String getTemperature() {
        jsonObject = getWeatherByCity(city);
        JSONObject main = null;
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
        JSONObject main = null;
        try {
            main = jsonObject.getJSONObject("main");
            double temperature = main.getDouble("pressure");
            return temperature + "hPa";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public Date getSunrise() {
        jsonObject = getWeatherByCity(city);
        try {
            long sunriseTimestamp = jsonObject.getJSONObject("sys").getLong("sunrise");
            Date sunriseDate = new Date(sunriseTimestamp * 1000);
            return sunriseDate;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public Date getSunset() {
        jsonObject = getWeatherByCity(city);
        try {
            long sunsetTimestamp = jsonObject.getJSONObject("sys").getLong("sunset");
            Date sunset = new Date(sunsetTimestamp);
            return sunset;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public String getWindSpeed(){
        jsonObject = getWeatherByCity(city);
        try {
            long wind = jsonObject.getJSONObject("wind").getLong("speed");
            return wind + " km/h";
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public String getCityName(){
        jsonObject = getWeatherByCity(city);
        try {
            String cityName = jsonObject.getString("name");
            return cityName;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}