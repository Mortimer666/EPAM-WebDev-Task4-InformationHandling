package com.epam.informationhandling.service.interpreter.expressions;

import com.epam.informationhandling.exception.InformationHandlingException;
import com.epam.informationhandling.service.interpreter.AbstractExpression;
import com.epam.informationhandling.service.interpreter.Context;

public class TerminalExpressionDivide implements AbstractExpression {
    @Override
    public void interpret(Context context) throws InformationHandlingException {
        Double secondMember = context.popValue();
        Double firstMember = context.popValue();

        if (secondMember == 0) {
            throw new InformationHandlingException("Dividing by zero");
        }
        context.pushValue(firstMember / secondMember);
    }
}
