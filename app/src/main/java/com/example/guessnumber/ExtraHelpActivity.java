package com.example.guessnumber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ExtraHelpActivity extends Activity {

    public static final String EXTRA_INDEX_OF_QUEST = "SUPER_HELP";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_help);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String hintValue = (String) bundle.get(EXTRA_INDEX_OF_QUEST);
            final EditText hintNumberView = findViewById(R.id.hintNumberLabel);
            hintNumberView.setText(hintValue);
        }
    }
}