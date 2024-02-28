package com.tdd;

import java.util.Arrays;

import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        return Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .reduce(0,Integer::sum);
    }
}
