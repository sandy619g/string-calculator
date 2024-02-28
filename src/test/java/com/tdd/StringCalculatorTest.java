package com.tdd;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    public StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void testEmptyString(){
        assertEquals(0,stringCalculator.add(""));
    }

    @Test
    public void testSingleNumber(){
        assertEquals(1,stringCalculator.add("1"));
    }

    @Test
    public void testTwoNumbers(){
        assertEquals(6,stringCalculator.add("1,5"));
    }
    

}
