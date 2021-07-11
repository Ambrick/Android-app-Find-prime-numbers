package com.example.guessnumber;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

    public static List<Integer> GeneratePrimeNumbersListWithBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        //Put every prime digit in the range between 2 and n in the list
        for (int i = 2; i <= n; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    //Check if number is Prime
    public static boolean isPrimeBruteForce(Integer number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer GenerateNonPrimeDigit(Integer digit_upper_boundary) {
        Integer random_integer = new Random().nextInt(digit_upper_boundary - 2) + 2;

        if (NumberGenerator.isPrimeBruteForce(random_integer))
            random_integer= GenerateNonPrimeDigit(digit_upper_boundary);

        return random_integer;
    }

    public static List<Integer> GetRandomNumbers(int total_numbers_count, int digit_upper_boundary) {
        List<Integer> Numbers = new LinkedList<>();

        List<Integer> PrimeNumbers = GeneratePrimeNumbersListWithBruteForce(digit_upper_boundary);
        //Get total count of prime numbers
        int prime_count = new Random().nextInt(total_numbers_count - 1);
        //Fill the list with random number of prime digits
        for (int i = 0; i < prime_count + 1; i++){
            int index_of_prime_number = new Random().nextInt(PrimeNumbers.size());
            Integer prime_digit = PrimeNumbers.get(index_of_prime_number);
            Numbers.add(prime_digit);
        }
        //Filling the list with non-prime digits
        for (int i = 0; i < total_numbers_count - prime_count - 1; i++)
            Numbers.add(GenerateNonPrimeDigit(digit_upper_boundary));
        //Shuffle the list
        Collections.shuffle(Numbers);
        return Numbers;
    }
}
