package com.ohayer.weatherapp.running;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.ohayer.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView cityName,wind,temperature,pressure,sunRise,sunSet, dateTime;
    public Async async;
    private ImageView conditionImg;
    private ConstraintLayout constraintLayout;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide the title theme
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        @SuppressLint("UseCompatLoadingForDrawables") Drawable icon = getResources().getDrawable(R.drawable.ic_action_name);


        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Error connecting to network")
                    .setCancelable(false)
                    .setPositiveButton(null, (dialog, id) -> recreate())
                    .setPositiveButtonIcon(icon);
            AlertDialog alert = builder.create();
            alert.show();
        } else {


            constraintLayout = findViewById(R.id.layoutMain);
            dateTime = findViewById(R.id.dateNowTxt);
            cityName = findViewById(R.id.cityName);
            wind = findViewById(R.id.windTxt);
            temperature = findViewById(R.id.tempTxt);
            pressure = findViewById(R.id.pressureTxt);
            sunRise = findViewById(R.id.sRiseTxt);
            sunSet = findViewById(R.id.sSetTxt);
            conditionImg = findViewById(R.id.weatherImgCondition);

            async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, dateTime, conditionImg, "Warsaw", constraintLayout, MainActivity.this).execute();
            editText = findViewById(R.id.editTxtCity);
        }
    }


    public void onBtnClick(View v) {
        String city = editText.getText().toString();
        async = (Async) new Async(cityName, wind, temperature, pressure, sunRise, sunSet, dateTime, conditionImg, city, constraintLayout, MainActivity.this).execute();
    }

}


