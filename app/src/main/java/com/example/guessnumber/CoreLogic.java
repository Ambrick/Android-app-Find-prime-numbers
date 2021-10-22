package com.example.guessnumber;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class CoreLogic {

    private final Toast myToast;
    private final List<Button> numbered_buttons_lst;
    private Integer prime_digits_counter = 0;

    public CoreLogic(List<Button> numbered_buttons_lst, Toast myToast){
        this.numbered_buttons_lst = numbered_buttons_lst;
        this.myToast = myToast;
        SetButtons();
    }

    //Sets generated numbers to buttons
    @SuppressLint("SetTextI18n")
    public void SetButtons(){
        //Shuffle the order of the number buttons
        Collections.shuffle(numbered_buttons_lst);
        //Set prime numbers counter to zero
        prime_digits_counter = 0;
        //Creating a list with filled with different numbers
        List<Integer> numbers = NumberGenerator.GetRandomNumbers(numbered_buttons_lst.size(), 99);
        //For every button get the random number and display it
        for (int i = 0; i < numbered_buttons_lst.size(); i++) {

            Integer digit = numbers.get(i);
            if (NumberGenerator.isPrimeBruteForce(digit))
                prime_digits_counter++;

            numbered_buttons_lst.get(i).setText(digit.toString());
            numbered_buttons_lst.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void CheckSelectedNumbers(View view) {
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
                ShowCorrectToast();
                SetButtons();
            }
            return;
        }
        //If button is filled with non-prime digit
        ShowIncorrectToast();
        SetButtons();
    }

    private void ShowCorrectToast() {
        myToast.setText(R.string.positive_toast);
        myToast.show();
    }

    private void ShowIncorrectToast() {
        myToast.setText(R.string.negative_toast);
        myToast.show();
    }
}
