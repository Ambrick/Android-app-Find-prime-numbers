package com.example.guessnumber;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PrimeDigitGenerator {

    public static List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static boolean isPrimeBruteForce(Integer number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer GenerateNonPrimeDigit(Integer number) {
        Integer random_integer = new Random().nextInt(number) + 2;
        if (PrimeDigitGenerator.isPrimeBruteForce(random_integer))
            random_integer= GenerateNonPrimeDigit(number);

        return random_integer;
    }
}
