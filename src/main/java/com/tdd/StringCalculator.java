package com.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.join;
import static java.util.regex.Pattern.compile;
import static java.util.regex.Pattern.quote;
import static org.springframework.util.StringUtils.isEmpty;

public class StringCalculator {

    public String DEFAULT_DELIMITER = "[,\n]";

    public String delimiter = "[,\n]";

    public int add(String numbers) {
        if(isEmpty(numbers)){
            return 0;
        }
        if (numbers.startsWith("//")) {
            delimiter = getCustomDelimeter(numbers);
            numbers = getNumbersString(numbers,delimiter);
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

    private String getNumbersString(String numbers, String delimiter) {
        if (delimiter.equals("0") || delimiter.equals("1")) {
            this.delimiter = DEFAULT_DELIMITER;
            int index = numbers.indexOf("\n");
            numbers = index != -1 ? numbers.substring(index + 1) : numbers;

            String[] delimiterOptions = delimiter.equals("0") ? new String[]{"[,0]"} : new String[]{"[,1]"};
            List<String> myNumbers = List.of(numbers.split(delimiterOptions[0]));
            List<Integer> myList = myNumbers.stream()
                    .filter(num -> !num.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            boolean isEven = delimiter.equals("0");
            return IntStream.range(0, myList.size())
                    .filter(i -> i % 2 == (isEven ? 0 : 1))
                    .mapToObj(myList::get)
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        }
        return getNumbersString(numbers);
    }


    private void handleNegatives(List<String> negatives) {
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("negative numbers not allowed " + join(",", negatives));
        }
    }
}
