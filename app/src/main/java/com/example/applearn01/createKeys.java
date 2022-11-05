package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class createKeys extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA_P = "com.example.applearn01.DATAP";
    public static final String EXTRA_DATA_Q = "com.example.applearn01.DATAQ";

    private EditText primeNumP;
    private EditText primeNumQ;
    private TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_output);

        error = findViewById(R.id.errorText);
        error.setTextColor(Color.RED);
        primeNumP = findViewById(R.id.primeNumP);
        primeNumQ = findViewById(R.id.primeNumQ);

        ((Button)findViewById(R.id.createKey)).setOnClickListener(this);
        ((Button)findViewById(R.id.back)).setOnClickListener(this);
        ((Button)findViewById(R.id.ran)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int p, q;

        switch (view.getId()) {
            case (R.id.ran):
                p = 0;
                q = 0;
                while(p == q) {
                    p = createPrimeNum();
                    q = createPrimeNum();
                }
                String strP = "" + p;
                String strQ = "" + q;
                primeNumP.setText(strP);
                primeNumQ.setText(strQ);
                break;

            case (R.id.back):
                Intent intentBack = new Intent(this, MainActivity.class);
                startActivity(intentBack);
                break;

            case (R.id.createKey):
                error.setText("");
                try {
                    p = Integer.parseInt(String.valueOf(primeNumP.getText()));
                    q = Integer.parseInt(String.valueOf(primeNumQ.getText()));

                    if(!isPrimeNum(p) || !isPrimeNum(q)) throw new Exception();

                    Intent intentPlane = new Intent(this, planeText.class);
                    intentPlane.putExtra(EXTRA_DATA_P, p);
                    intentPlane.putExtra(EXTRA_DATA_Q, q);
                    startActivity(intentPlane);

                } catch (Exception e) {

                    error.setText("なんかえらー");

                }
                break;
            default:
        }

    }

    public static int createPrimeNum() {
        Random r = new Random();
        int result = -1;
        boolean conFlag = true;
        while(conFlag) {
            result = r.nextInt(999900) + 100;
            if(isPrimeNum(result)) conFlag = false;
        }
        return result;
    }

    public static boolean isPrimeNum(int n) {
        int sq = (int)Math.sqrt(n);
        for(int i=2; i<=sq; i++) {
            if(n%i == 0) {
                return false;
            }
        }
        return true;
    }

}