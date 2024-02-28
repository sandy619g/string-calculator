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
    public void testTwoNumbers(){
        assertEquals(6,stringCalculator.add("1,5"));
    }

    @Test
    public void testMultipleNumbers(){
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
    }
}
