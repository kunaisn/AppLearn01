package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        title.setText("RSA暗号を試す");

        ((Button)findViewById(R.id.startButton)).setOnClickListener(this);
        start = findViewById(R.id.startButton);
        start.setText("はじめる");

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case (R.id.startButton):
                title.setText("changeText");
                break;
            default:
                break;
        }

    }


}