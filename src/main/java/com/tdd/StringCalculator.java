package com.tdd;

import java.util.Arrays;

import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        return Arrays.stream(numbers.split("[,\n]"))
                .peek(s -> {
                    if (isEmpty(s)) {
                        throw new IllegalArgumentException("Input contains an invalid value.");
                    }
                })
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
