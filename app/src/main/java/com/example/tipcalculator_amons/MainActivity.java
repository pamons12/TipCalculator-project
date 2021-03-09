package com.example.tipcalculator_amons;

import androidx.annotation.NonNull;
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

        //Setup EditTexts
        checkTotalEditText = findViewById(R.id.checkTotalEditText);
        numOfPeopleEditText = findViewById(R.id.numOfPeopleEditText);
        tipPercentageTextView = findViewById(R.id.tipPercentageTextView);

        //For the calculate button, checks to make sure entrys are valid and than sends
        // user to new activity disaplyTipActivity, displaying the results
        calculateTipIntent = new Intent(this, displayTipActivity.class);
        calculateButton = (Button) findViewById(R.id.calculateTipButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validCheckTotalEntry = false;
                boolean validNumOfPeopleEntry = false;
                boolean validTipPercent = false;

                //Check to make sure check total field is not empty
                if (checkTotalEditText.getText().toString().isEmpty()){
                    showErrorAlert(getString(R.string.enter_check_total_error_label),checkTotalEditText.getId());
                    validCheckTotalEntry = false;
                } else {
                    checkTotal = Double.parseDouble(checkTotalEditText.getText().toString());

                    //Check to make sure check total is not <= 0
                    if (checkTotal<=0){
                        showErrorAlert(getString(R.string.check_total_less_than_0_error_label),checkTotalEditText.getId());
                        validCheckTotalEntry = false;
                    } else{
                        validCheckTotalEntry = true;
                    }
                }

                //Check to make sure num of people field is not empty
                if (numOfPeopleEditText.getText().toString().isEmpty()){
                    showErrorAlert(getString(R.string.enter_number_of_people_error_label),numOfPeopleEditText.getId());
                    validNumOfPeopleEntry = false;
                } else {
                    numOfPeople = Integer.parseInt(numOfPeopleEditText.getText().toString());

                    //Check to make sure num of people is not <= 0
                    if (numOfPeople<=0){
                        showErrorAlert(getString(R.string.num_of_people_less_than_1_error_label),numOfPeopleEditText.getId());
                        validNumOfPeopleEntry = false;
                    } else {
                        validNumOfPeopleEntry = true;
                    }
                }

                //Check to make sure tip percent is not <= 0
                if (tipPercent<=0){
                    showErrorAlert(getString(R.string.tip_percent_less_than_0_error_label),customPercentButton.getId());
                    validTipPercent = false;
                } else {
                    validTipPercent = true;
                }

                //If all 3 conditions are satisfied than go to displayTipActivity
                if (validCheckTotalEntry && validNumOfPeopleEntry && validTipPercent){
                    calculateTipIntent.putExtra("checkTotal",checkTotal);
                    calculateTipIntent.putExtra("numOfPeople",numOfPeople);
                    calculateTipIntent.putExtra("tipPercent",tipPercent);
                    startActivityForResult(calculateTipIntent,REQUEST_CODE);
                }

            }
        });

        //For customPercentButton, when pressed will send user to customTipActivity so they can
        // enter a custom  tip amount
        customTipIntent = new Intent(this, customTipActivity.class);
        customPercentButton = (Button) findViewById(R.id.customPerButton);
        customPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(customTipIntent,REQUEST_CODE);
            }
        });

        //For reset button, resets check total, tip percent, and number of people to 0
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTotal=0;
                tipPercent=0;
                numOfPeople=0;
                tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
            }
        });

        //For 10 percent button, when clicked will set tip percent to 10
        tenPercentButton = (Button) findViewById(R.id._10PerButton);
        tenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=10;
                tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
            }
        });

        //For 15 percent button, when clicked will set tip percent to 15
        fifteenPercentButton = (Button) findViewById(R.id._15PerButton);
        fifteenPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=15;
                tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
            }
        });

        ////For 20 percent button, when clicked will set tip percent to 20
        twentyPercentButton = (Button) findViewById(R.id._20PerButton);
        twentyPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercent=20;
                tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
            }
        });

        //Check to see if save state != null
        //If not null will load values from save state into numOfPeople, checkTotal, and tipPercent
        if (savedInstanceState != null){
            numOfPeople = savedInstanceState.getInt("numOfPeople");
            checkTotal = savedInstanceState.getDouble("checkTotal");
            tipPercent = savedInstanceState.getDouble("tipPercent");
            tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
        } else {
            numOfPeople = 0;
            checkTotal = 0;
            tipPercent = 0;
        }

    }

    //Used to collect responses from other activitys when they are done
    // resultCode == 1 -> customTipActivity
    // resultCode == 2 -> displayTipActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();

        //From customTipActivity
        if (resultCode==1) {
            tipPercent = extras.getDouble("tipPercent");
            tipPercentageTextView.setText(getString(R.string.current_tip_percent_label) + " " + tipPercent + "%");
        }

        //From display tip activity, resets values to 0
        if (resultCode==2){
            resetButton.performClick();
        }
    }

    //Displays an error message for the user to see if called
    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.error_message_title_label))
                .setMessage(errorMessage)
                .setNeutralButton(getString(R.string.error_message_close_button_label),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }

    //Saves variables numOfPeople, checkTotal, and tipPercent in a save state in case screen is rotated
    @Override
    protected void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putInt("numOfPeople",numOfPeople);
        saveState.putDouble("checkTotal",checkTotal);
        saveState.putDouble("tipPercent",tipPercent);
    }
}