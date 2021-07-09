package com.example.guessnumber;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class NumberGenerator {

    public static List<Integer> GetGeneratedNumbers(int count) {
        List<Integer> Numbers = new LinkedList<>();

        List<Integer> PrimeNumbers = PrimeDigitGenerator.primeNumbersBruteForce(99);

        int prime_count = new Random().nextInt(count - 1);

        for (int i = 0; i < prime_count + 1; i++){
            int index_of_prime_number = new Random().nextInt(PrimeNumbers.size());
            Integer prime_digit = PrimeNumbers.get(index_of_prime_number);
            Numbers.add(prime_digit);
        }

        for (int i = 0; i < count - prime_count - 1; i++)
            Numbers.add(PrimeDigitGenerator.GenerateNonPrimeDigit(98));

        return Numbers;
    }

}
