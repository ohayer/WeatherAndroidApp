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

    public JSONObject getWeatherByCity(String city) {
        String realLink = ApiLink.replace("CITY", city);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(realLink)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 404){
                return null;
            }
            String jsonStr = response.body().string();
            return new JSONObject(jsonStr);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e + " Error with JSON");
        }
    }
}
