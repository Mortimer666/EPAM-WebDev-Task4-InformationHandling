package com.epam.informationhandling.service.interpreter.expressions;

import com.epam.informationhandling.service.interpreter.AbstractExpression;
import com.epam.informationhandling.service.interpreter.Context;

public class TerminalExpressionMultiply implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        Double result = context.popValue() * context.popValue();
        context.pushValue(result);
    }
}
