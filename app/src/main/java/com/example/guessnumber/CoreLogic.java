package com.example.guessnumber;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class CoreLogic {
    private final NumberGenerator numberGenerator = new NumberGenerator();
    private final List<Button> numbered_buttons_lst;
    private final Toast myToast;
    private Integer prime_digits_generated_counter;
    private Integer prime_digits_found_counter;

    public CoreLogic(List<Button> numbered_buttons_lst, Toast myToast){
        this.numbered_buttons_lst = numbered_buttons_lst;
        this.myToast = myToast;
        SetButtons();
    }

    //Sets generated numbers to buttons
    @SuppressLint("SetTextI18n")
    private void SetButtons(){
        //Shuffle the order of the number buttons
        Collections.shuffle(numbered_buttons_lst);
        //Set prime numbers counter to zero
        prime_digits_generated_counter = 0;
        prime_digits_found_counter = 0;
        //Creating a list with filled with different numbers
        List<Integer> numbers = numberGenerator.GetRandomNumbers(numbered_buttons_lst.size(), 99);
        //For every button get the random number and display it
        for (int i = 0; i < numbered_buttons_lst.size(); i++) {
            Integer digit = numbers.get(i);
            if (numberGenerator.isPrimeBruteForce(digit))
                prime_digits_generated_counter++;

            numbered_buttons_lst.get(i).setText(digit.toString());
            numbered_buttons_lst.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void CheckSelectedNumbers(View view) {
        Button currentButton = (Button) view;
        Integer buttonValue = Integer.parseInt((String)currentButton.getText());
        //If button has prime number as a text, then
        if (numberGenerator.isPrimeBruteForce(buttonValue)) {
            prime_digits_found_counter++;
            //Check if all prime numbers were found
            if (prime_digits_generated_counter.equals(prime_digits_found_counter)) {
                myToast.setText(R.string.positive_toast);
                myToast.show();
                SetButtons();
            } else {
                currentButton.setVisibility(View.INVISIBLE);
            }
            return;
        }
        //If button is filled with non-prime digit
        myToast.setText(R.string.negative_toast);
        myToast.show();
        SetButtons();
    }
}
