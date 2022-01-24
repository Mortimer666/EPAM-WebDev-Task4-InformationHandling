package com.epam.informationhandling.service.interpreter;

import com.epam.informationhandling.exception.InformationHandlingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

class ExpressionCalculatorTest {
    private static final double DELTA = 0.1;
    private static final ExpressionCalculator expressionCalculator = new ExpressionCalculator();
    private static final Map<String, Double> parameters = new HashMap<String, Double>() {
        {
            put("x", 2.0);
        }
    };

    @ParameterizedTest
    @CsvSource(value = {"[8 x +]=10", "[8 x -]=6", "[8 x /]=4", "[8 x *]=16"}, delimiter = '=')
    void testCalculateShouldReturnCorrectDoubleWhenExpressionIsCorrect(String expression, double expected) throws InformationHandlingException {
        //given with data provider
        //when
        double actual = expressionCalculator.calculate(expression, parameters);
        //then
        Assertions.assertEquals(expected, actual, DELTA);
    }

    @Test
    void testCalculateShouldThrowExceptionWhenExpressionHasUnknownVariable() throws NoSuchElementException {
        //given
        String expression = "[8 y *]";
        //when
        InformationHandlingException thrown = Assertions.assertThrows(InformationHandlingException.class,
                () -> expressionCalculator.calculate(expression, parameters));
        //then
        Assertions.assertEquals("There is unknown variable in expression: y", thrown.getMessage());
    }
}
