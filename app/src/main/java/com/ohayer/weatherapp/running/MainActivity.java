package com.ohayer.weatherapp.running;


import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ohayer.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView cityName;
    private TextView wind;
    private TextView temperature;
    private TextView pressure;
    private TextView sunRise;
    private TextView sunSet, dateTime;
    public Async async;
    private ImageView conditionImg;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide the title theme
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.layoutMain);
        dateTime = findViewById(R.id.dateNowTxt);
        cityName = findViewById(R.id.cityName);
        wind = findViewById(R.id.windTxt);
        temperature = findViewById(R.id.tempTxt);
        pressure = findViewById(R.id.pressureTxt);
        sunRise = findViewById(R.id.sRiseTxt);
        sunSet = findViewById(R.id.sSetTxt);
        conditionImg = findViewById(R.id.weatherImgCondition);

        async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, dateTime, conditionImg, "Warsaw", constraintLayout).execute();
        editText = findViewById(R.id.editTxtCity);
    }


    public void onBtnClick(View v) {
        String city = editText.getText().toString();
        async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, dateTime, conditionImg, city, constraintLayout).execute();
    }

}


