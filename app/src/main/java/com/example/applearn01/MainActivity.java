package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title);

        ((BUtton)findViewById(R.id.startButton)).setOnClickListener(this);



    }

    @Override
    public void onClick() {

        swhich(view.getId()) {
            case (R.id.staetButton):
                break;
        }

    }


}