package com.example.tipcalculator_amons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText checkTotalEditText;
    private EditText numOfPeopleEditText;

    private Button tenPercentButton;
    private Button fifteenPercentButton;
    private Button twentyPercentButton;
    private Button customPercentButton;
    private Button resetButton;
    private Button calculateButton;

    private int checkTotal;
    private double tipPercent;
    private float numOfPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkTotalEditText = findViewById(R.id.checkTotalEditText);
        numOfPeopleEditText = findViewById(R.id.numOfPeopleEditText);

        tenPercentButton = (Button) findViewById(R.id._10PerButton);
        tenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.1;
                Toast.makeText(getApplicationContext(),"10% Selected",Toast.LENGTH_SHORT).show();
            }
        });
        fifteenPercentButton = (Button) findViewById(R.id._15PerButton);
        fifteenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.15;
                Toast.makeText(getApplicationContext(),"15% Selected",Toast.LENGTH_SHORT).show();
            }
        });
        twentyPercentButton = (Button) findViewById(R.id._20PerButton);
        twentyPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.2;
                Toast.makeText(getApplicationContext(),"20% Selected",Toast.LENGTH_SHORT).show();
            }
        });

    }
}