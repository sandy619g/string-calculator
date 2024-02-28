package com.tdd;

import org.springframework.util.StringUtils;

public class StringCalculator {

    public int add(String s) {
        if(s==""){
            return 0;
        }
        else {
            return Integer.parseInt(s);
        }
    }
}
