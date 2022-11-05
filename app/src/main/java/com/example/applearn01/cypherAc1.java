package com.example.applearn01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class cypherAc1 extends AppCompatActivity implements View.OnClickListener {

    long n, e, d;
    EditText planeText;
    TextView error;
    MultiAutoCompleteTextView cypText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cypher_ac1);

        Intent intent = getIntent();
        n = intent.getLongExtra("com.example.applearn01.DATAN", -1);
        e = intent.getLongExtra("com.example.applearn01.DATAE", -1);
        d = intent.getLongExtra(planeText2.EXTRA_DATA_D, -1);
        TextView pubKey = findViewById(R.id.pubKey);
        pubKey.setText("公開鍵：（" + n + ", " + e + "）");

        ((Button)findViewById(R.id.back)).setOnClickListener(this);
        ((Button)findViewById(R.id.cluCyp)).setOnClickListener(this);
        ((Button)findViewById(R.id.next)).setOnClickListener(this);

        planeText = findViewById(R.id.planeText);
        cypText = findViewById(R.id.cypText);

        error = findViewById(R.id.error);
        error.setTextColor(Color.RED);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.back):
                Intent intentBack = new Intent(this, createKeys.class);
                startActivity(intentBack);
                break;

            case (R.id.cluCyp):
                String str = String.valueOf(planeText.getText());
                char[] cypherChar = new char[str.length()];
                for (int i = 0; i < str.length(); i++) {
                    int c = str.charAt(i);
                    long cyp = c;
                    for (int j = 0; j < e; j++) {
                        cyp = (cyp * c + n) % n;
                    }
                    cypherChar[i] = (char) cyp;

                }
                break;

            case (R.id.next):
                error.setText("");
                try {

                    if (d == -1) throw new Exception();
                    Intent intentPub = new Intent(this, planeText2.class);
                    startActivity(intentPub);

                } catch (Exception e) {

                    error.setText("なんかえらー");

                }

            default:
        }
    }

}