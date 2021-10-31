package com.example.guessnumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NumberGenerator {

    private final ArrayList<Integer> listWithPrimeNumbers = new ArrayList<>();
    private final ArrayList<Integer> listWithNonPrimeNumbers =  new ArrayList<>();

    public NumberGenerator (Integer upper_boundary) {
        //Filling the lists with prime and nonPrime numbers
        for (int i = 2; i <= upper_boundary; i++) {
            if (isPrimeByBruteForce(i))
                listWithPrimeNumbers.add(i);
            else
                listWithNonPrimeNumbers.add(i);
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
        return listWithPrimeNumbers.contains(number);
    }

    //Return list, filled with generated numbers
    public ArrayList<Integer> GetListWithRandomNumbers(int total_buttons_count) {

        int primeCount = 1 + new Random().nextInt(total_buttons_count - 1);
        ArrayList<Integer> generatedListWithPrimeDigits = GenerateListWithDigitsFromList(primeCount, listWithPrimeNumbers);

        int nonPrimeCount = total_buttons_count - primeCount;
        ArrayList<Integer> generatedListWithNonPrimeDigits = GenerateListWithDigitsFromList(nonPrimeCount, listWithNonPrimeNumbers);

        generatedListWithPrimeDigits.addAll(generatedListWithNonPrimeDigits);
        //Shuffle the list
        Collections.shuffle(generatedListWithPrimeDigits);
        return generatedListWithPrimeDigits;
    }

    //Generate list filled with random digits from list
    private ArrayList<Integer> GenerateListWithDigitsFromList(int finalListSize, ArrayList<Integer> listWithDigits){
        ArrayList<Integer> finalList = new ArrayList<>(finalListSize);

        for (int i = 0; i < finalListSize; i++){
            int indexOfPrimeDigit = (int) (Math.random() * (listWithDigits.size() - 1));
            int primeDigit = listWithDigits.get(indexOfPrimeDigit);
            finalList.add(primeDigit);
        }
        return finalList;
    }
}
