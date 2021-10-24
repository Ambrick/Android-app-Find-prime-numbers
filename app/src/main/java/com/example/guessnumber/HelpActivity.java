package com.example.guessnumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends Activity {

    public static final String EXTRA_INDEX_OF_QUEST = "SUPER_HELP";
    private String hintValue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Bundle bundle = getIntent().getExtras();

        if (bundle!=null)
            hintValue = (String) bundle.get("SUPER_HELP");
    }

    public void OnClickOnExtraHelpButton(View view) {
        Intent intent = new Intent(HelpActivity.this, ExtraHelpActivity.class);
        intent.putExtra(HelpActivity.EXTRA_INDEX_OF_QUEST, hintValue);
        startActivity(intent);
    }
}