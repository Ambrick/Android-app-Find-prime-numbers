package com.example.guessnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toast myToast;
    private List<Button> numbered_buttons_lst = new LinkedList<>();
    private Integer prime_digits_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        numbered_buttons_lst.add(findViewById(R.id.button_zero));
        numbered_buttons_lst.add(findViewById(R.id.button_one));
        numbered_buttons_lst.add(findViewById(R.id.button_two));
        numbered_buttons_lst.add(findViewById(R.id.button_three));
        numbered_buttons_lst.add(findViewById(R.id.button_four));
        numbered_buttons_lst.add(findViewById(R.id.button_five));
        prime_digits_counter = 0;
        SetButtons();
    }

    private void SetButtons(){
        Collections.shuffle(numbered_buttons_lst);
        prime_digits_counter = 0;
        List<Integer> numbers = NumberGenerator.GetGeneratedNumbers(6);
        for (int i = 0; i < numbered_buttons_lst.size(); i++) {
            Integer digit = numbers.get(i);

            if (PrimeDigitGenerator.isPrimeBruteForce(digit))
                prime_digits_counter++;

            numbered_buttons_lst.get(i).setText(digit.toString());
            numbered_buttons_lst.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void OnClickOnNumberButton(View view) {
        Button currentButton = (Button) view;
        String buttonText = (String) currentButton.getText();
        Integer buttonValue = Integer.parseInt(buttonText);

        if (PrimeDigitGenerator.isPrimeBruteForce(buttonValue)) {
            currentButton.setVisibility(View.INVISIBLE);

            Integer invisible_buttons_count = 0;
            for (int i = 0; i < numbered_buttons_lst.size(); i++) {
                if (numbered_buttons_lst.get(i).getVisibility() == View.INVISIBLE)
                    invisible_buttons_count++;
            }
            if (prime_digits_counter.equals( invisible_buttons_count)) {
                myToast.setText(R.string.positive_toast);
                myToast.show();
                SetButtons();
            }
            return;
        }

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