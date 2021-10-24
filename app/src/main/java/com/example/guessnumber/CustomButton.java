package com.example.guessnumber;

import android.view.View;
import android.widget.Button;

public class CustomButton {

    private Button button_view;
    private String button_text;
    private Boolean is_selected;

    CustomButton(Button button_view, String button_text, Boolean is_selected) {
        this.button_view = button_view;
        this.button_text = button_text;
        this.is_selected = is_selected;
        ShowCustomButton();
    }

    public void ShowCustomButton() {
        this.button_view.setText(button_text);
        SetVisibilityOfTheButton();
    }

    private void SetVisibilityOfTheButton() {
        if (is_selected)
            this.button_view.setVisibility(View.INVISIBLE);
        else
            this.button_view.setVisibility(View.VISIBLE);
    }

    public void CustomButtonWasSelected() {
        is_selected = true;
        button_view.setVisibility(View.INVISIBLE);
    }

    public Boolean CheckIfEqualButton(Button another_view) {
        return button_view.equals(another_view);
    }

    public String GetValue(){
        return button_text;
    }

    public String GetIfSelected() {
        return is_selected ? "1" : "0";
    }
}
