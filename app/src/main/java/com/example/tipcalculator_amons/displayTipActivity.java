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

        tipAmountTextView = findViewById(R.id.tipTextView);
        totalWithoutTipTextView = findViewById(R.id.totalWithoutTipTextView);
        totalWithTipTextView = findViewById(R.id.totalWithTipTextView);
        totalPerPersonTextView = findViewById(R.id.totalPerPersonTextView);

        displayTipIntent = getIntent();
        extras = displayTipIntent.getExtras();
        numOfPeople = extras.getInt("numOfPeople");
        checkTotal = extras.getDouble("checkTotal");
        tipPercent = extras.getDouble("tipPercent");

        tipAmount = checkTotal*(tipPercent/100);
        tipAmountTextView.setText("Tip Amount: " + String.valueOf(tipAmount));
        totalWithoutTipTextView.setText("Total Not Including Tip: " + String.valueOf(checkTotal));
        totalWithTipTextView.setText("Total Including Tip: " + String.valueOf(tipAmount+checkTotal));
        totalPerPersonTextView.setText("Total Per Person: " + String.valueOf((tipAmount+checkTotal)/numOfPeople));

        resetButton = (Button) findViewById(R.id.resetButtonDisplayTip);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void finish() {
        Intent returnDisplayTipIntent = new Intent();
        setResult(2, returnDisplayTipIntent);
        super.finish();
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
