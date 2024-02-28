package com.tdd;

import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public int add(String s) {
        if(isEmpty(s)){
            return 0;
        }
        return Integer.parseInt(s);
    }
}
