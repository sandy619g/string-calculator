package com.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.join;
import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.quote;
import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public String delimiter = "[,\n]";

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        if (numbers.startsWith("//")) {
            delimiter = getCustomDelimeter(numbers);
            numbers = getNumbersString(numbers);
        }
        List<String> negatives = new ArrayList<>();
        int sum = Arrays.stream(numbers.split(delimiter))
                .peek(s -> {
                    if (isEmpty(s)) {
                        throw new IllegalArgumentException("Input contains an invalid value.");
                    }
                    try {
                        if(Integer.parseInt(s)<0){
                            negatives.add(s);
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Input contains a non-integer value.");
                    }
                })
                .filter(num -> Integer.parseInt(num) <= 1000)
                .mapToInt(Integer::parseInt)
                .sum();
        handleNegatives(negatives);
        return sum;
    }

    private String getCustomDelimeter(String numbers) {
        delimiter = numbers.substring(2, numbers.indexOf("\n"));
        if(delimiter.length()>1) {
            delimiter = getCustLengthDelimiter(delimiter);
        }
        return delimiter;
    }

    private String getCustLengthDelimiter(String numbers) {
        StringBuilder patternBuilder = new StringBuilder();

        Pattern pattern = compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(numbers);

        while (matcher.find()) {
            patternBuilder.append(quote(matcher.group(1)));
            patternBuilder.append("|");
        }
        patternBuilder.append(",");
        patternBuilder.append("\n");
        return patternBuilder.toString();
    }

    private String getNumbersString(String numbers) {
        int index = numbers.indexOf("\n");
        return index != -1 ? numbers.substring(index + 1) : numbers;
    }

    private void handleNegatives(List<String> negatives) {
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed " + join(",", negatives));
        }
    }
}
