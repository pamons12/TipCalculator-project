package com.example.tipcalculator_amons;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class customTipActivity extends AppCompatActivity {

    private EditText customTipEditText;
    private Button confirmButton;
    private double tipPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tip);

        //Initialize editText
        customTipEditText = (EditText) findViewById(R.id.customTipValue);

        //For confirmButton, when pressed take value from edit text and send it back to MainActivity
        confirmButton = (Button) findViewById(R.id.confirmTipButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextReturnValue = customTipEditText.getText().toString();

                //Checks to make sure edit text is not empty
                if(customTipEditText.getText().toString().isEmpty()){
                    showErrorAlert(getString(R.string.empty_percent_error_label),customTipEditText.getId());
                } else {
                    tipPercent = Double.parseDouble(customTipEditText.getText().toString());
                    finish();
                }
            }
        });
    }

    //Called when ready to send values back to MainActivity
    // puts tipPercent in an Intent and sends it back with result code of 1
    @Override
    public void finish() {
        Intent customTipIntent = new Intent();
        customTipIntent.putExtra("tipPercent", tipPercent);
        setResult(1, customTipIntent);
        super.finish();
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
}
