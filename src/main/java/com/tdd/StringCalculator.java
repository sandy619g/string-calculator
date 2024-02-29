package com.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.join;
import static java.util.regex.Pattern.quote;
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
            if(delimiter.length()>1) {
                delimiter = getCustDelimiter(delimiter);
            }
        }
        List<String> negatives = new ArrayList<>();
        int sum = Arrays.stream(numbers.split(quote(delimiter) + "|,|\n"))
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

    private String getCustDelimiter(String delimiter) {
        String patternString = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(delimiter);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

}
