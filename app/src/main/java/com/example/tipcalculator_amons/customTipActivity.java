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

        customTipEditText = (EditText) findViewById(R.id.customTipValue);

        confirmButton = (Button) findViewById(R.id.confirmTipButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextReturnValue = customTipEditText.getText().toString();
                if(customTipEditText.getText().toString().isEmpty()){
                    showErrorAlert("Empty Percent Field",customTipEditText.getId());
                } else {
                    tipPercent = Double.parseDouble(customTipEditText.getText().toString());
                    finish();
                }
            }
        });
    }

    @Override
    public void finish() {
        Intent customTipIntent = new Intent();
        customTipIntent.putExtra("tipPercent", tipPercent);
        setResult(1, customTipIntent);
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
