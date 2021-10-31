package com.example.guessnumber;

import android.widget.Button;
import java.util.ArrayList;

public class CustomButtonsManager{

    private final Integer rangeForGeneratingRandomDigits= 99;
    private final NumberGenerator numberGenerator= new NumberGenerator(rangeForGeneratingRandomDigits);

    private ArrayList<CustomButton> customButtonList = new ArrayList<>();
    private Integer generatedPrimeDigitsCounter = 0;
    private Integer selectedPrimeDigitsCounter = 0;

    public Boolean CheckIfPrime(Integer value) {
        return numberGenerator.isPrime(value);
    }

    public Boolean CheckIfAllPrimeNumbersAreSelected(){
        return generatedPrimeDigitsCounter.equals(selectedPrimeDigitsCounter);
    }

    public void ChangeSelectedStateInCustomButton(Button button_view){
        for (CustomButton button : customButtonList) {
            if (button.CheckIfEqualButton(button_view)) {
                button.CustomButtonWasSelected();
                selectedPrimeDigitsCounter++;
            }
        }
    }

    void NullifyQuestDigits(){
        generatedPrimeDigitsCounter = 0;
        selectedPrimeDigitsCounter = 0;
        customButtonList.clear();
    }

    //You give new Button View that was recreated in MainActivity to saved buttons
    public void GetNewDigits(ArrayList<Button> buttonsList){
        NullifyQuestDigits();
        //Creating a list with filled with different numbers
        ArrayList<Integer> numbers = numberGenerator.GetListWithRandomNumbers(buttonsList.size());
        //For every button get the random number and display it
        for (int i = 0; i < buttonsList.size(); i++) {
            Integer digit = numbers.get(i);
            customButtonList.add(new CustomButton(buttonsList.get(i), digit.toString(), false));

            if (numberGenerator.isPrime(digit))
                generatedPrimeDigitsCounter++;
        }
    }

    public void ImplementSavedQuestState(ArrayList<Button> buttonList,
                                         ArrayList<String> valuesList,
                                         ArrayList<String> isSelectedList) {
        NullifyQuestDigits();
        for (int i = 0; i < buttonList.size(); i++)
        {
            String value = valuesList.get(i);
            Boolean isSelected = false;
            if (isSelectedList.get(i).equals("1")) {
                isSelected = true;
                selectedPrimeDigitsCounter++;
            }

            if (numberGenerator.isPrime(Integer.parseInt(value)))
                generatedPrimeDigitsCounter++;

            customButtonList.add(new CustomButton(buttonList.get(i), value, isSelected));
        }
    }

    public String GivePrimeNumberFromGeneratedDigits(){
        for (CustomButton button : customButtonList) {
            Integer value = Integer.parseInt(button.GetValue());
            if (numberGenerator.isPrime(value) && button.GetIfSelected().equals("0"))
                return button.GetValue();
        }
        return "";
    }

    public ArrayList<String> GetCurrentDigitsList() {
        ArrayList<String> final_array = new ArrayList<>();

        for (CustomButton button : customButtonList)
            final_array.add(button.GetValue());

        return final_array;
    }

    public ArrayList<String> GetCurrentSelectedList() {
        ArrayList<String> final_array = new ArrayList<>();

        for (CustomButton button : customButtonList)
            final_array.add(button.GetIfSelected());

        return final_array;
    }
}
