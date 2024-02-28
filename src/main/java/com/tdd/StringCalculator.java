package com.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.join;
import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public static String delimiter = "";

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);
        }
        List<String> negatives = new ArrayList<>();
        int sum = Arrays.stream(numbers.split("[,\n" + delimiter + "]"))
                .peek(s -> {
                    if (isEmpty(s)) {
                        throw new IllegalArgumentException("Input contains an invalid value.");
                    }
                    if(Integer.parseInt(s)<0){
                        negatives.add(s);
                    }
                })
                .filter(num -> Integer.parseInt(num) <= 1000)
                .mapToInt(Integer::parseInt)
                .sum();
        if(!negatives.isEmpty()){
            throw new IllegalArgumentException("negative numbers not allowed "+join(",",negatives));
        }
        return sum;
    }

}
