package com.example.guessnumber;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    public static final String EXTRA_INDEX_OF_QUEST = "EXTRA_HELP_VALUE";
    private String hintValue;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
            hintValue = (String) bundle.get(EXTRA_INDEX_OF_QUEST);

        setResult(123);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 321) {
                        Intent data = result.getData();
                        String hintValue = data.getStringExtra("HINT_VALUE");

                        Intent intent = new Intent();
                        intent.putExtra("HINT_VALUE", hintValue);
                        setResult(321, intent);
                    }
                }
            });

    public void OnClickOnExtraHelpButton(View view) {
        Intent intent = new Intent(this, ExtraHelpActivity.class);
        intent.putExtra(HelpActivity.EXTRA_INDEX_OF_QUEST, hintValue);
        someActivityResultLauncher.launch(intent);
    }
}