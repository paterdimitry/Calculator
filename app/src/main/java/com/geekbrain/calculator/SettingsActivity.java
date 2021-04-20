package com.geekbrain.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private static final String THEME = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initButtons();
    }

    private void initButtons() {
        Button matrixButton = findViewById(R.id.matrixButton);
        matrixButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(THEME, R.style.MatrixStyle);
            startActivity(intent);

        });
        Button mathButton = findViewById(R.id.mathButton);
        mathButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(THEME, R.style.Theme_AppCompat_Light);
            startActivity(intent);

        });
    }
}