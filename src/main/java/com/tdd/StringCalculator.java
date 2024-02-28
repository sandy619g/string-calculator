package com.tdd;

import java.util.Arrays;

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
        return Arrays.stream(numbers.split("[,\n" + delimiter + "]"))
                .peek(s -> {
                    if (isEmpty(s)) {
                        throw new IllegalArgumentException("Input contains an invalid value.");
                    }
                    if(Integer.parseInt(s)<0){
                        throw new IllegalArgumentException("negative numbers not allowed "+s);
                    }
                })
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
