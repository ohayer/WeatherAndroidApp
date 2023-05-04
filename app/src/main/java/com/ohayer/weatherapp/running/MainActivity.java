package com.ohayer.weatherapp.running;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.ohayer.weatherapp.R;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView textView;
    private Async async;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.helloWorld);
        async = (Async) new Async(textView, "Warsaw").execute();
        editText = findViewById(R.id.editTxtCity);


    }


    public void onBtnClick(View v) {
        String city = editText.getText().toString();
        async = (Async) new Async(textView, city).execute();
        try {
            if (async.get().equals("City not found")) {
                async =(Async) new Async(textView, "Warsaw").execute();
                Toast.makeText(this, "That city does not exist", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}