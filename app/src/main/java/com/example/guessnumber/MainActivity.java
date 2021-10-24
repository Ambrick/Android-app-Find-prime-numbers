package com.example.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("customButtonsManager", questLogic.GetCurrentCustomButtonsManager());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        CustomButtonsManager customButtonsManager = (CustomButtonsManager)
                savedInstanceState.getSerializable("customButtonsManager");

        questLogic.ImplementSavedCustomButtonsManager(customButtonsManager);
    }

    public void OnClickOnNumberButton(View view) {
        questLogic.CheckSelectedNumbers(view);
    }

    public void OnClickOnHelpButton(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void OnClickOnInfoButton(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}