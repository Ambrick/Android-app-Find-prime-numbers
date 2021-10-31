package com.example.guessnumber;

import android.view.View;
import android.widget.Button;

public class CustomButton {

    private Button buttonView;
    private String buttonText;
    private Boolean isSelected;

    CustomButton(Button buttonView, String button_text, Boolean is_selected) {
        this.buttonView = buttonView;
        this.buttonText = button_text;
        this.isSelected = is_selected;
        ShowCustomButton();
    }

    public void ShowCustomButton() {
        this.buttonView.setText(buttonText);
        SetVisibilityOfTheButton();
    }

    private void SetVisibilityOfTheButton() {
        if (isSelected)
            this.buttonView.setVisibility(View.INVISIBLE);
        else
            this.buttonView.setVisibility(View.VISIBLE);
    }

    public void CustomButtonWasSelected() {
        isSelected = true;
        buttonView.setVisibility(View.INVISIBLE);
    }

    public Boolean CheckIfEqualButton(Button anotherView) {
        return buttonView.equals(anotherView);
    }

    public String GetValue(){
        return buttonText;
    }

    public String GetIfSelected() {
        return isSelected ? "1" : "0";
    }
}
