package com.example.guessnumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

    private final ArrayList<Integer> list_with_prime_numbers = new ArrayList<>();
    private final ArrayList<Integer> list_with_non_prime_numbers=  new ArrayList<>();

    public NumberGenerator (Integer upper_boundary) {
        //Filling the lists with prime and nonPrime numbers
        for (int i = 2; i <= upper_boundary; i++) {
            if (isPrimeByBruteForce(i))
                list_with_prime_numbers.add(i);
            else
                list_with_non_prime_numbers.add(i);
        }
    }

    //Check if number is Prime by BruteForce
    private boolean isPrimeByBruteForce(Integer number) {
        for (int i = 2; i < number; i++)
            if (number % i == 0)
                return false;
                
        return true;
    }

    //Check if number is Prime
    public boolean isPrime(Integer number) {
        return list_with_prime_numbers.contains(number);
    }

    //Return list, filled with generated numbers
    public ArrayList<Integer> GetListWithRandomNumbers(int total_buttons_count) {

        int primeCount = 1 + new Random().nextInt(total_buttons_count - 1);
        ArrayList<Integer> generatedListWithPrimeDigits = GenerateListWithDigitsFromList(primeCount, list_with_prime_numbers);

        int nonPrimeCount = total_buttons_count - primeCount;
        ArrayList<Integer> generatedListWithNonPrimeDigits = GenerateListWithDigitsFromList(nonPrimeCount, list_with_non_prime_numbers);

        generatedListWithPrimeDigits.addAll(generatedListWithNonPrimeDigits);
        //Shuffle the list
        Collections.shuffle(generatedListWithPrimeDigits);
        return generatedListWithPrimeDigits;
    }

    //Generate list filled with random digits from list
    private ArrayList<Integer> GenerateListWithDigitsFromList(int listSize, ArrayList<Integer> listWithDigits){
        ArrayList<Integer> finalList = new ArrayList<>();
        int SizeOfFinalList = listWithDigits.size();

        for (int i = 0; i < listSize; i++){
            int index_of_prime_digit = (int) (Math.random() * (SizeOfFinalList - 1));
            int prime_digit = listWithDigits.get(index_of_prime_digit);
            finalList.add(prime_digit);
        }
        return finalList;
    }
}
