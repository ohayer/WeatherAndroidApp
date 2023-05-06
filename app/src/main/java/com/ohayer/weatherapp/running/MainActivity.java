package com.ohayer.weatherapp.running;


import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ohayer.weatherapp.R;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView cityName;
    private TextView wind;
    private TextView temperature;
    private TextView pressure;
    private TextView sunRise;
    private TextView sunSet;
    private Async async;
    private ImageView conditionImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide the title theme
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView dateOfToday = findViewById(R.id.dateNowTxt);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate date = LocalDate.now();
            dateOfToday.setText(date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear());
        }

        cityName = findViewById(R.id.cityName);
        wind = findViewById(R.id.windTxt);
        temperature = findViewById(R.id.tempTxt);
        pressure = findViewById(R.id.pressureTxt);
        sunRise = findViewById(R.id.sRiseTxt);
        sunSet = findViewById(R.id.sSetTxt);
        conditionImg = findViewById(R.id.weatherImgCondition);

        async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, conditionImg, "Warsaw").execute();
        editText = findViewById(R.id.editTxtCity);
    }


    public void onBtnClick(View v) {
        String city = editText.getText().toString();
        async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, conditionImg, city).execute();
        if (async.weatherProperties.isEmpty()) {
            Toast.makeText(this,"City does not found",Toast.LENGTH_LONG).show();
        }
    }

}


