package com.example.tipcalculator_amons;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class displayTipActivity extends AppCompatActivity {

    private TextView tipAmountTextView;
    private TextView totalWithoutTipTextView;
    private TextView totalWithTipTextView;
    private TextView totalPerPersonTextView;

    private Button resetButton;

    private Intent displayTipIntent;
    private Bundle extras;

    private int numOfPeople;
    private double checkTotal;
    private double tipPercent;
    private double tipAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_tip);

        //Initilize textViews
        tipAmountTextView = findViewById(R.id.tipTextView);
        totalWithoutTipTextView = findViewById(R.id.totalWithoutTipTextView);
        totalWithTipTextView = findViewById(R.id.totalWithTipTextView);
        totalPerPersonTextView = findViewById(R.id.totalPerPersonTextView);

        //Initilize Intent and pull values from it and store in variables numOfPeople, checkTotal, and tipPercent
        displayTipIntent = getIntent();
        extras = displayTipIntent.getExtras();
        numOfPeople = extras.getInt("numOfPeople");
        checkTotal = extras.getDouble("checkTotal");
        tipPercent = extras.getDouble("tipPercent");

        //Calculate tip amount, totals, and displays them via the textViews
        tipAmount = checkTotal*(tipPercent/100);
        tipAmountTextView.setText("Tip Amount: " + String.valueOf(tipAmount));
        totalWithoutTipTextView.setText("Total Not Including Tip: " + String.valueOf(checkTotal));
        totalWithTipTextView.setText("Total Including Tip: " + String.valueOf(tipAmount+checkTotal));
        totalPerPersonTextView.setText("Total Per Person: " + String.valueOf((tipAmount+checkTotal)/numOfPeople));

        //For the resetButton, calls finish()
        resetButton = (Button) findViewById(R.id.resetButtonDisplayTip);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //Called when ready to send values back to MainActivity
    // sends resultCode of 2 to MainActivity so it knows to reset
    @Override
    public void finish() {
        Intent returnDisplayTipIntent = new Intent();
        setResult(2, returnDisplayTipIntent);
        super.finish();
    }

    //Displays an error message for the user to see if called
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
