package es.ucm.fdi.calculator;

import org.junit.Test;
import es.ucm.fdi.calculator.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void addition_isCorrect() {
        Calculator cal = new Calculator();
        assertEquals( (long)4, (long)cal.add(2,2));
    }
}