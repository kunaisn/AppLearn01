package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class planeText extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA_E = "com.example.applearn01.DATAE";
    public static final String EXTRA_DATA_N = "com.example.applearn01.DATAN";
    public static final String EXTRA_DATA_L = "com.example.applearn01.DATAL";

    TextView textE;
    TextView error2;
    long l;
    long numPQ;
    long e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_text);

        Intent intent = getIntent();
        int p = intent.getIntExtra(createKeys.EXTRA_DATA_P, -1);
        int q = intent.getIntExtra(createKeys.EXTRA_DATA_Q, -1);
        l = lcm((p - 1),(q - 1));
        numPQ = (long)p * q;
        e = -1;

        TextView textP = findViewById(R.id.numberP);
        TextView textQ = findViewById(R.id.numberQ);
        TextView textN = findViewById(R.id.numberN);
        TextView textL = findViewById(R.id.numberL);
        textE = findViewById(R.id.numberE);
        ((Button)findViewById(R.id.back2)).setOnClickListener(this);
        ((Button)findViewById(R.id.buttonE)).setOnClickListener(this);
        ((Button)findViewById(R.id.next4)).setOnClickListener(this);

        String strP = "" + p;
        textP.setText(strP);
        String strQ = "" + q;
        textQ.setText(strQ);
        String strN = "" + numPQ;
        textN.setText(strN);
        String strL = "" + l;
        textL.setText(strL);

        error2 = findViewById(R.id.error2);
        error2.setTextColor(Color.RED);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.back2):
                Intent intentBack = new Intent(this, createKeys.class);
                startActivity(intentBack);
                break;

            case (R.id.buttonE):
                e = createE(l);
                String strE = "" + e;
                textE.setText(strE);
                break;

            case (R.id.next4):
                error2.setText("");
                try {

                    if(e == -1) throw new Exception();
                    Intent intentPub = new Intent(this, planeText2.class);
                    intentPub.putExtra(EXTRA_DATA_E, e);
                    intentPub.putExtra(EXTRA_DATA_N, numPQ);
                    intentPub.putExtra(EXTRA_DATA_L, l);
                    startActivity(intentPub);

                } catch (Exception e) {

                    error2.setText("なんかえらー");

                }

            default:
        }
    }

    public static long lcm(int n, int m) {
        long max = Math.max(n, m);
        long min = Math.min(n, m);
        long c = max * min;
        long q = n % m;
        while(q != 0) {
            max = min;
            min = q;
            q = max % min;
        }
        return c/min;
    }

    public static long createE(long num) {
        Random r = new Random();
        long gcdNum = 0;
        long ePro = 0;
        while(gcdNum != 1) {
            ePro = r.nextInt(9999000) + 1000;
            gcdNum = gcd(ePro, num);
        }
        return ePro;
    }

    public static long gcd(long ePro, long num) {
        long upper = Math.max(ePro, num);
        long lower = Math.min(ePro, num);
        long q = upper % lower;
        if(q == 0) return lower;
        return gcd(lower, q);
    }

}