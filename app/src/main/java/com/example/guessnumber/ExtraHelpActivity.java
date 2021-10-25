package com.example.guessnumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ExtraHelpActivity extends AppCompatActivity {

    public static final String EXTRA_INDEX_OF_QUEST = "EXTRA_HELP_VALUE";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_help);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String hintValue = (String) bundle.get(EXTRA_INDEX_OF_QUEST);
            final EditText hintNumberView = findViewById(R.id.hintNumberLabel);
            hintNumberView.setText(hintValue);

            Intent intent = new Intent();
            intent.putExtra("HINT_VALUE", hintValue);
            setResult(321, intent);
        }
    }
}