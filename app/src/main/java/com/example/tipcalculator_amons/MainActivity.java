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
    private Intent calculateTipIntent;

    private int numOfPeople;
    private double checkTotal;
    private double tipPercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkTotalEditText = findViewById(R.id.checkTotalEditText);
        numOfPeopleEditText = findViewById(R.id.numOfPeopleEditText);
        tipPercentageTextView = findViewById(R.id.tipPercentageTextView);

        calculateTipIntent = new Intent(this, displayTipActivity.class);
        calculateButton = (Button) findViewById(R.id.calculateTipButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validCheckTotalEntry = false;
                boolean validNumOfPeopleEntry = false;
                boolean validTipPercent = false;
                if (checkTotalEditText.getText().toString().isEmpty()){
                    showErrorAlert("Enter check total",checkTotalEditText.getId());
                    validCheckTotalEntry = false;
                } else {
                    checkTotal = Double.parseDouble(checkTotalEditText.getText().toString());
                    if (checkTotal<=0){
                        showErrorAlert("Check total cannot be less than or equal to 0",checkTotalEditText.getId());
                        validCheckTotalEntry = false;
                    } else{
                        validCheckTotalEntry = true;
                    }
                }
                if (numOfPeopleEditText.getText().toString().isEmpty()){
                    showErrorAlert("Enter number of people to split check with",numOfPeopleEditText.getId());
                    validNumOfPeopleEntry = false;
                } else {
                    numOfPeople = Integer.parseInt(numOfPeopleEditText.getText().toString());
                    if (numOfPeople<=0){
                        showErrorAlert("Number of people cannot be less than 1",numOfPeopleEditText.getId());
                        validNumOfPeopleEntry = false;
                    } else {
                        validNumOfPeopleEntry = true;
                    }
                }
                if (tipPercent<=0){
                    showErrorAlert("Tip percent cannot be less than or equal to 0",customPercentButton.getId());
                    validTipPercent = false;
                } else {
                    validTipPercent = true;
                }
                if (validCheckTotalEntry && validNumOfPeopleEntry && validTipPercent){
                    calculateTipIntent.putExtra("checkTotal",checkTotal);
                    calculateTipIntent.putExtra("numOfPeople",numOfPeople);
                    calculateTipIntent.putExtra("tipPercent",tipPercent);
                    startActivityForResult(calculateTipIntent,REQUEST_CODE);
                }

            }
        });

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
                tipPercentageTextView.setText("Current Tip Percentage: "+tipPercent+"%");
            }
        });

        tenPercentButton = (Button) findViewById(R.id._10PerButton);
        tenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=10;
                tipPercentageTextView.setText("Current Tip Percentage: "+tipPercent+"%");
            }
        });

        fifteenPercentButton = (Button) findViewById(R.id._15PerButton);
        fifteenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=15;
                tipPercentageTextView.setText("Current Tip Percentage: "+tipPercent+"%");
            }
        });

        twentyPercentButton = (Button) findViewById(R.id._20PerButton);
        twentyPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=20;
                tipPercentageTextView.setText("Current Tip Percentage: "+tipPercent+"%");
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
            tipPercentageTextView.setText("Current Tip Percentage: "+tipPercent+"%");
        }

        //From display tip activity
        if (resultCode==2){
            resetButton.performClick();
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