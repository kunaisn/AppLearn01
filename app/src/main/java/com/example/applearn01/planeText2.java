package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class planeText2 extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA_D = "com.example.applearn01.DATAD";

    TextView error3;
    TextView textSecKey;
    long l;
    long e;
    long n;
    long d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_text2);

        Intent intent = getIntent();
        e = intent.getLongExtra(planeText.EXTRA_DATA_E, -1);
        n = intent.getLongExtra(planeText.EXTRA_DATA_N, -1);
        l = intent.getLongExtra(planeText.EXTRA_DATA_L, -1);
        d = -1;

        TextView textPubKey = findViewById(R.id.pubKey);
        textSecKey = findViewById(R.id.secKey);
        ((Button)findViewById(R.id.back3)).setOnClickListener(this);
        ((Button)findViewById(R.id.next5)).setOnClickListener(this);
        ((Button)findViewById(R.id.createSecKey)).setOnClickListener(this);

        String strPubKey = "（" + n + ", " + e + "）";
        textPubKey.setText(strPubKey);
        String strSecKey = "（" + n + ", D）";
        textSecKey.setText(strSecKey);

        error3 = findViewById(R.id.error3);
        error3.setTextColor(Color.RED);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.back3):
                Intent intentBack = new Intent(this, createKeys.class);
                startActivity(intentBack);
                break;

            case (R.id.createSecKey):
                d = createD(e);
                if(d == -1) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "秘密鍵が見つかりませんでした",
                            Toast.LENGTH_SHORT
                    );
                    toast.show();
                    Intent intentBack2 = new Intent(this, createKeys.class);
                    startActivity(intentBack2);
                    break;
                }
                String strSecKey = "（" + n + ", " + d + "）";
                textSecKey.setText(strSecKey);
                break;

            case (R.id.next5):
                error3.setText("");
                try {

                    if(d == -1) throw new Exception();
                    Intent intentPub = new Intent(this, planeText2.class);
                    intentPub.putExtra(planeText.EXTRA_DATA_N, n);
                    intentPub.putExtra(planeText.EXTRA_DATA_E, e);
                    intentPub.putExtra(EXTRA_DATA_D, d);
                    startActivity(intentPub);

                } catch (Exception e) {

                    error3.setText("なんかえらー");

                }

            default:
        }

    }

    public long createD(long num) {
        long q = 0;
        for(int i=1000; i<1000000; i++) {
            q = num * i % l;
            if(q == 1) return i;
        }
        return -1;
    }

}