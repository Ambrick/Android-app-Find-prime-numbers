package com.example.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoreLogic coreLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the list with "number buttons"
        List<Button> button_list = Arrays.asList(
                findViewById(R.id.button_zero),
                findViewById(R.id.button_one),
                findViewById(R.id.button_two),
                findViewById(R.id.button_three),
                findViewById(R.id.button_four),
                findViewById(R.id.button_five));
        Toast toast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        coreLogic = new CoreLogic(button_list,toast);
        coreLogic.SetButtons();
    }

    public void OnClickOnNumberButton(View view) {
        coreLogic.CheckSelectedNumbers(view);
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