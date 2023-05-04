package com.ohayer.weatherapp.running;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ohayer.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView cityName;
    private TextView wind;
    private TextView temperature;
    private Async async;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        wind = findViewById(R.id.windTxt);
        temperature = findViewById(R.id.tempTxt);
        async = (Async) new Async(cityName,wind,temperature, "Warsaw").execute();
        editText = findViewById(R.id.editTxtCity);


    }


    public void onBtnClick(View v) {
        String city = editText.getText().toString();
        async = (Async) new Async(cityName,wind,temperature, city).execute();
        if (async == null) {
            async = (Async) new Async(cityName,wind,temperature, "Warsaw").execute();
            Toast.makeText(this, "That city does not exist", Toast.LENGTH_SHORT).show();
        }


    }


}