package com.ohayer.weatherapp.connect;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ApiConnect {
    private final String ApiKey = "eed3a8b552860960a71dc0a13ebcd97d";
    private final String ApiLink = "https://api.openweathermap.org/data/2.5/weather?q=CITY&units=metric&appid=" + ApiKey;

//    public String getWeatherByCity(String city) {
//        String realLink = ApiLink.replace("CITY", city);
//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url(realLink)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            if (response.code() == 404){
//                return "City not found";
//            }
//            String jsonStr = response.body().string();
//            JSONObject jsonObject = new JSONObject(jsonStr);
//            JSONObject main = jsonObject.getJSONObject("main");
//            double temperature = main.getDouble("temp");
//            int realTemperature = (int) Math.round(temperature);
//            return String.valueOf(realTemperature);
//        } catch (IOException | JSONException e) {
//            throw new RuntimeException(e + " Error with JSON");
//        }
//    }
    public JSONObject getWeatherByCity(String city) {
        String realLink = ApiLink.replace("CITY", city);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(realLink)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 404){
                //TODO: catch error
            }
            String jsonStr = response.body().string();
            return new JSONObject(jsonStr);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e + " Error with JSON");
        }
    }
}
