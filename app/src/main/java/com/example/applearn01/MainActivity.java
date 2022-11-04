package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title);
        String appTitle = "RSA暗号を試す";
        title.setText(appTitle);

        ((Button)findViewById(R.id.startButton)).setOnClickListener(this);
        Button start = findViewById(R.id.startButton);
        start.setText("はじめる");

    }

    @Override
    public void onClick(View view) {

        Intent intentKeys = new Intent(this, createKeys.class);
        startActivity(intentKeys);

    }

}