package com.example.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toast myToast;
    private List<Button> numbered_buttons_lst = new LinkedList<>();
    private Integer prime_digits_counter = 0;
    private Integer digit_upper_boundary = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        numbered_buttons_lst = Arrays.asList(
                findViewById(R.id.button_zero),
                findViewById(R.id.button_one),
                findViewById(R.id.button_two),
                findViewById(R.id.button_three),
                findViewById(R.id.button_four),
                findViewById(R.id.button_five));

        SetButtons();
    }

    //Sets generated numbers to buttons
    private void SetButtons(){
        //Shuffle the order of the number buttons
        Collections.shuffle(numbered_buttons_lst);
        //Set prime numbers counter to zero
        prime_digits_counter = 0;
        //Creating a list with filled with different numbers
        List<Integer> numbers = NumberGenerator.GetRandomNumbers(numbered_buttons_lst.size(), digit_upper_boundary);
        //For every button get the random number and display it
        for (int i = 0; i < numbered_buttons_lst.size(); i++) {

            Integer digit = numbers.get(i);
            if (NumberGenerator.isPrimeBruteForce(digit))
                prime_digits_counter++;

            numbered_buttons_lst.get(i).setText(digit.toString());
            numbered_buttons_lst.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void OnClickOnNumberButton(View view) {
        Button currentButton = (Button) view;
        Integer buttonValue = Integer.parseInt((String) currentButton.getText());
        //If button has prime number as a text, then
        if (NumberGenerator.isPrimeBruteForce(buttonValue)) {
            currentButton.setVisibility(View.INVISIBLE);
            Integer invisible_buttons_count = 0;
            //Get the number of clicked buttons with prime digit
            for (int i = 0; i < numbered_buttons_lst.size(); i++) {
                if (numbered_buttons_lst.get(i).getVisibility() == View.INVISIBLE)
                    invisible_buttons_count++;
            }
            //Check if all prime numbers were found
            if (prime_digits_counter.equals( invisible_buttons_count)) {
                myToast.setText(R.string.positive_toast);
                myToast.show();
                SetButtons();
            }
            return;
        }
        //If button is filled with non-prime digit
        myToast.setText(R.string.negative_toast);
        myToast.show();
        SetButtons();
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