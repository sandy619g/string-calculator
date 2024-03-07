package com.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    public StringCalculator stringCalculator = new StringCalculator();

    @Test
    @DisplayName("should return 0 when empty string")
    public void testEmptyString(){
        assertEquals(0,stringCalculator.add(""));
    }

    @Test
    @DisplayName("should return 0 when null string")
    public void testNullString(){
        assertEquals(0,stringCalculator.add(null));
    }

    @Test
    @DisplayName("should return number on single string")
    public void testSingleNumber(){
        assertEquals(1,stringCalculator.add("1"));
    }

    @Test
    @DisplayName("should return sum for multiple numbers")
    public void testMultipleNumbers(){
        assertEquals(6,stringCalculator.add("1,5"));
        assertEquals(18,stringCalculator.add("1,5,12"));
    }

    @Test
    @DisplayName("should return sum with new line delimiter string")
    public void testNewLineDelimiter(){
        assertEquals(16,stringCalculator.add("1\n2,13"));
    }

    @Test
    @DisplayName("should throw error for invalid delimiter pattern")
    public void testInvalidLineDelimiter(){
        assertThrows(IllegalArgumentException.class,()->stringCalculator.add("1,\n,2"));
    }

    @Test
    @DisplayName("should return sum for custom delimiter")
    public void testCustomDelimiter(){
        assertEquals(3,stringCalculator.add("//;\n1;2"));
        assertEquals(35,stringCalculator.add("//#\n10#25"));
    }

    @Test
    @DisplayName("should throw exception for negative numbers")
    public void testNegativeNumbers(){
        assertEquals("negative numbers not allowed -2", assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("1,-2,3");
        }).getMessage());
        assertEquals("negative numbers not allowed -2,-3,-9",  assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("1,-2,-3,-9");
        }).getMessage());
    }

    @Test
    @DisplayName("should return sum by ignoring value greater than 1000")
    public void testNumberGreaterThan1000(){
        assertEquals(1002,stringCalculator.add("1000,2"));
        assertEquals(2,stringCalculator.add("1001,2"));
    }

    @Test
    @DisplayName("should return sum for lengthy custom delimiter")
    public void testCustomLengthDelimiter(){
        assertEquals(6,stringCalculator.add("//[***]\n1***2***3"));
        assertEquals(6,stringCalculator.add("//[####]\n1####2####3"));
    }


    @Test
    @DisplayName("should return sum for multiple custom delimiters")
    public void testMultipleCustomDelimiter(){
        assertEquals(6,stringCalculator.add("//[*][%]\n1*2%3"));
        assertEquals(6,stringCalculator.add("//[***][%%%]\n1***2%%%3"));
    }

    @Test
    @DisplayName("should throw exception for non integer values")
    public void testNonIntegerValues(){
        assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("1,hi,3");
        });
    }

    @Test
    @DisplayName("should return sum for custom delimiter")
    //0 only sum the num even indices
    //1 then odd indices
    public void testCustomOddEvenDelimiter(){
        assertEquals(4,stringCalculator.add("//0\n1,2,3,4"));
        assertEquals(4,stringCalculator.add("//0\n1,2,3,0,4"));
        assertEquals(6,stringCalculator.add("//1\n0,2,3,4"));
        assertEquals(6,stringCalculator.add("//1\n0,2,3,1,4"));
    }

}
