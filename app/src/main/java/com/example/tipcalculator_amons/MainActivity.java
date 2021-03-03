package com.example.tipcalculator_amons;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;

    private EditText checkTotalEditText;
    private EditText numOfPeopleEditText;

    private TextView tipPercentageTextView;

    private Button tenPercentButton;
    private Button fifteenPercentButton;
    private Button twentyPercentButton;
    private Button customPercentButton;
    private Button resetButton;
    private Button calculateButton;

    private Intent customTipIntent;

    private int checkTotal;
    private double tipPercent;
    private float numOfPeople;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkTotalEditText = findViewById(R.id.checkTotalEditText);
        numOfPeopleEditText = findViewById(R.id.numOfPeopleEditText);
        tipPercentageTextView = findViewById(R.id.tipPercentageTextView);


        customTipIntent = new Intent(this, customTipActivity.class);
        customPercentButton = (Button) findViewById(R.id.customPerButton);
        customPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(customTipIntent,REQUEST_CODE);
            }
        });

        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTotal=0;
                tipPercent=0;
                numOfPeople=0;
            }
        });

        tenPercentButton = (Button) findViewById(R.id._10PerButton);
        tenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.1;
                Toast.makeText(getApplicationContext(),"10% Tip Selected",Toast.LENGTH_SHORT).show();
            }
        });

        fifteenPercentButton = (Button) findViewById(R.id._15PerButton);
        fifteenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.15;
                Toast.makeText(getApplicationContext(),"15% Tip Selected",Toast.LENGTH_SHORT).show();
            }
        });

        twentyPercentButton = (Button) findViewById(R.id._20PerButton);
        twentyPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=.2;
                Toast.makeText(getApplicationContext(),"20% Tip Selected",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();

        //From customTipActivity
        if (resultCode==1) {
            tipPercent = extras.getDouble("tipPercent");
            Toast.makeText(getApplicationContext(),tipPercent+"% Tip Selected",Toast.LENGTH_SHORT).show();
        }
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }
}