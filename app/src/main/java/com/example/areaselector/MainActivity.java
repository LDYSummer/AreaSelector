package com.example.areaselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelector();
            }
        });


    }

    private void initSelector(){

        new AreaSelector(this)
                .showSelector()
                .setOnSelectedResultListener(new OnSelectedResultListener() {
                    @Override
                    public void SelectedData(String province, String city, String area, String street) {
                        btn.setText(province + city + area + street);
                    }
                });

    }
}