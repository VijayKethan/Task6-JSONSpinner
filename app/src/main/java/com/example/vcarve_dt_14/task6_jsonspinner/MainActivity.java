package com.example.vcarve_dt_14.task6_jsonspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import com.example.vcarve_dt_14.task6_jsonspinner.JSONDownloader;

import static com.example.vcarve_dt_14.task6_jsonspinner.R.id.btn;

public class MainActivity extends AppCompatActivity {

    String jsonURL = "http://services.groupkt.com/country/get/all";
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sp = (Spinner)findViewById(R.id.sp);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONDownloader(MainActivity.this,jsonURL,sp).execute();
            }
        });
    }
}
