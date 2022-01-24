package com.epam.informationhandling.service.interpreter.expressions;

import com.epam.informationhandling.service.interpreter.AbstractExpression;
import com.epam.informationhandling.service.interpreter.Context;

public class NonTerminalExpression implements AbstractExpression {
    private final Double number;

    public NonTerminalExpression(Double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
