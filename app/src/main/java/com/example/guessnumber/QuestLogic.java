package com.example.guessnumber;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestLogic implements Serializable {

    private CustomButtonsManager customButtonsManager;
    private NumberGenerator numberGenerator;
    private Toast myToast;
    private ArrayList<Button> button_list;
    private Integer upper_boundary = 99;

    public QuestLogic(ArrayList<Button> buttons_list, Toast myToast){
        numberGenerator = new NumberGenerator(upper_boundary);
        this.myToast = myToast;
        this.button_list = buttons_list;

        RefreshCustomButtonManager();
    }

    public CustomButtonsManager GetCurrentCustomButtonsManager(){
        return customButtonsManager;
    }

    public void ImplementSavedCustomButtonsManager(CustomButtonsManager customButtonsManager){
        this.customButtonsManager = customButtonsManager;
        this.customButtonsManager.MergeButtons(button_list);
        this.customButtonsManager.ShowButtons();
    }

    private void RefreshCustomButtonManager(){
        this.customButtonsManager = new CustomButtonsManager();
        //Creating a list with filled with different numbers
        ArrayList<Integer> numbers = numberGenerator.GetListWithRandomNumbers(button_list.size());
        //For every button get the random number and display it
        for (int i = 0; i < button_list.size(); i++) {
            Button button = button_list.get(i);
            Integer digit = numbers.get(i);
            if (numberGenerator.isPrime(digit))
                customButtonsManager.AddCustomButton(button, digit.toString(), true);
            else
                customButtonsManager.AddCustomButton(button, digit.toString(), false);
        }
    }

    public void CheckSelectedNumbers(View view) {
        Button currentButton = (Button) view;
        Integer buttonValue = Integer.parseInt((String)currentButton.getText());
        //If button has prime number as a text, then
        if (numberGenerator.isPrime(buttonValue)) {
            customButtonsManager.ChangeSelectedStateInCustomButton(currentButton);
            //Check if all prime numbers were found
            if (customButtonsManager.CheckIfAllPrimeNumbersAreSelected()) {
                myToast.setText(R.string.positive_toast);
                myToast.show();
                RefreshCustomButtonManager();
            }
            return;
        }
        //If button is filled with non-prime digit
        myToast.setText(R.string.negative_toast);
        myToast.show();
        RefreshCustomButtonManager();
    }
}
