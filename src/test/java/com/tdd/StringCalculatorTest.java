package com.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    public StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void testEmptyString(){
        assertEquals(0,stringCalculator.add(""));
    }

    @Test
    public void testNullString(){
        assertEquals(0,stringCalculator.add(null));
    }

    @Test
    public void testSingleNumber(){
        assertEquals(1,stringCalculator.add("1"));
    }

    @Test
    public void testMultipleNumbers(){
        assertEquals(6,stringCalculator.add("1,5"));
        assertEquals(18,stringCalculator.add("1,5,12"));
    }

    @Test
    public void testNewLineDelimiter(){
        assertEquals(16,stringCalculator.add("1\n2,13"));
    }

    @Test
    public void testInvalidLineDelimiter(){
        assertThrows(IllegalArgumentException.class,()->stringCalculator.add("1,\n,2"));
    }

    @Test
    public void testCustomDelimiter(){
        assertEquals(3,stringCalculator.add("//;\n1;2"));
        assertEquals(35,stringCalculator.add("//#\n10#25"));
    }

    @Test
    public void testNegativeNumbers(){
        assertEquals("negative numbers not allowed -2", assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("1,-2,3");
        }).getMessage());
        assertEquals("negative numbers not allowed -2,-3,-9",  assertThrows(IllegalArgumentException.class, () -> {
            stringCalculator.add("1,-2,-3,-9");
        }).getMessage());
    }

    @Test
    public void testNumberGreaterThan1000(){
        assertEquals(1002,stringCalculator.add("1000,2"));
        assertEquals(2,stringCalculator.add("1001,2"));
    }

    @Test
    public void testCustomLengthDelimiter(){
        assertEquals(6,stringCalculator.add("//[***]\n1***2***3"));
        assertEquals(6,stringCalculator.add("//[####]\n1####2####3"));
    }
}
