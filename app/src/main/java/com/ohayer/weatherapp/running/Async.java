package com.ohayer.weatherapp.running;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;
import com.ohayer.weatherapp.connect.ApiConnect;

public class Async extends AsyncTask<Void, Void, String> {
    @SuppressLint("StaticFieldLeak")
    private final TextView textView;

    public Async(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... params) {
        ApiConnect apiConnect = new ApiConnect();
        return apiConnect.getWeatherByCity("London");
    }


    @Override
    protected void onPostExecute(String result) {
        textView.setText("Temperatura w "+ "London" +  " to: " + result + "°C");
    }
}
