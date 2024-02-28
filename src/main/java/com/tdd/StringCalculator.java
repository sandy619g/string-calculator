package com.tdd;

import java.util.Arrays;

import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public static final String REGEX = "[,\n]";

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        return Arrays.stream(numbers.split(REGEX))
                .peek(s -> {
                    if (isEmpty(s)) {
                        throw new IllegalArgumentException("Input contains an invalid value.");
                    }
                })
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
