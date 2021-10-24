package com.example.guessnumber;

import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomButtonsManager implements Serializable {

    private class CustomButton{
        private Button button_view;
        private String button_text;
        private Boolean is_selected;

        CustomButton (Button button_view, String button_text, Boolean is_prime){
            this.button_view = button_view;
            this.button_text = button_text;
            this.is_selected = false;

            ShowCustomButton();
        }

        public void ShowCustomButton(){
            this.button_view.setText(button_text);
            SetVisibilityOfTheButton();
        }

        public void RefreshView(Button new_view){
            button_view = new_view;
            SetVisibilityOfTheButton();
        }

        private void SetVisibilityOfTheButton(){
            if (is_selected)
                this.button_view.setVisibility(View.INVISIBLE);
            else
                this.button_view.setVisibility(View.VISIBLE);
        }

        public void CustomButtonWasSelected(){
            is_selected = true;
            button_view.setVisibility(View.INVISIBLE);
        }

        public Boolean CheckIfEqualButton(Button another_view){
            return button_view.equals(another_view);
        }
    }

    private ArrayList<CustomButton> customButtonList = new ArrayList<>();
    private Integer generated_prime_digits_counter = 0;
    private Integer selected_prime_digits_counter = 0;

    public void AddCustomButton(Button button_view, String button_text, Boolean isPrime){
        customButtonList.add(new CustomButton(button_view, button_text, isPrime));
        if (isPrime)
            generated_prime_digits_counter++;
    }

    public Boolean CheckIfAllPrimeNumbersAreSelected(){
        return generated_prime_digits_counter.equals(selected_prime_digits_counter);
    }

    public void ChangeSelectedStateInCustomButton(Button button_view){
        for (CustomButton button : customButtonList) {
            if (button.CheckIfEqualButton(button_view)) {
                button.CustomButtonWasSelected();
                selected_prime_digits_counter++;
            }
        }
    }

    public void ShowButtons(){
        for (CustomButton button : customButtonList)
            button.ShowCustomButton();
    }

    //You give new Button View that was recreated in MainActivity to saved buttons
    public void MergeButtons(ArrayList<Button> new_list_of_button_views){
        ArrayList<Button> copy_of_array = new ArrayList<>(new_list_of_button_views);

        for (CustomButton button : customButtonList)
            button.RefreshView(copy_of_array.remove(0));
    }
}
