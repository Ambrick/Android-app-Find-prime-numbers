package com.example.guessnumber;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private QuestLogic questLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Button> button_list = new ArrayList<>(Arrays.asList(
                findViewById(R.id.button_zero),
                findViewById(R.id.button_one),
                findViewById(R.id.button_two),
                findViewById(R.id.button_three),
                findViewById(R.id.button_four),
                findViewById(R.id.button_five)));
        final Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        questLogic = new QuestLogic(button_list, toast);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 123)
                        questLogic.MakeAToast(getString(R.string.help_was_used_toast));
                    else if (result.getResultCode() == 321) {
                        Intent data = result.getData();
                        String hintValue = data.getStringExtra("HINT_VALUE");
                        questLogic.MakeAToast(getString(R.string.extra_help_was_used_toast) + hintValue);
                    }
                }
            });

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("quest_buttons_digit", questLogic.GetCurrentDigitsList());
        savedInstanceState.putStringArrayList("quest_buttons_isSelected", questLogic.GetCurrentSelectedList());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        final ArrayList<String> quest_buttons_digit_list = savedInstanceState.getStringArrayList("quest_buttons_digit");
        final ArrayList<String> quest_buttons_isSelected_list = savedInstanceState.getStringArrayList("quest_buttons_isSelected");

        questLogic.ImplementSavedQuestState(quest_buttons_digit_list, quest_buttons_isSelected_list);
    }

    public void OnClickOnHelpButton(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        intent.putExtra(HelpActivity.EXTRA_INDEX_OF_QUEST, questLogic.GiveAHint());
        someActivityResultLauncher.launch(intent);
    }

    public void OnClickOnInfoButton(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void OnClickOnNumberButton(View view) {
        questLogic.CheckSelectedNumbers(view);
    }
}
