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

//Класс управляет игровое логикой: контролирует выбор чисел,
//проверяет условия успешного и не успешного выбора и выдает соответствующую информацию
public class QuestLogic{

    private CustomButtonsManager customButtonsManager;
    private ArrayList<Button> button_list;
    private Toast myToast;

    public QuestLogic(ArrayList<Button> buttons_list, Toast myToast){
        this.customButtonsManager = new CustomButtonsManager();
        this.myToast = myToast;
        this.button_list = buttons_list;

        customButtonsManager.GetNewDigits(button_list);
    }

    public void CheckSelectedNumbers(View view) {
        Button currentButton = (Button) view;
        Integer buttonValue = Integer.parseInt((String)currentButton.getText());

        //If button has prime number as a text, then
        if (customButtonsManager.CheckIfPrime(buttonValue)) {
            customButtonsManager.ChangeSelectedStateInCustomButton(currentButton);
            //Check if all prime numbers were found
            if (customButtonsManager.CheckIfAllPrimeNumbersAreSelected()) {
                MakeAToast(R.string.positive_toast);
                customButtonsManager.GetNewDigits(button_list);
            }
            else
                MakeAToast(R.string.more_toast);
            return;
        }
        //If button is filled with non-prime digit
        MakeAToast(R.string.negative_toast);
        customButtonsManager.GetNewDigits(button_list);
    }

    public void MakeAToast(String toastMessage) {
        myToast.setText(toastMessage);
        myToast.show();
    }

    public void MakeAToast(int toastMessage) {
        myToast.setText(toastMessage);
        myToast.show();
    }

    public String GiveAHint(){
        return customButtonsManager.GivePrimeNumberFromGeneratedDigits();
    }

    public ArrayList<String> GetCurrentDigitsList() {
        return customButtonsManager.GetCurrentDigitsList();
    }

    public ArrayList<String> GetCurrentSelectedList() {
        return customButtonsManager.GetCurrentSelectedList();
    }

    public void ImplementSavedQuestState(ArrayList<String> valuesList, ArrayList<String> isSelectedList) {
        customButtonsManager.ImplementSavedQuestState(this.button_list, valuesList, isSelectedList);
    }
}
