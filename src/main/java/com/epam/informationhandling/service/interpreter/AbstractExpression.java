package com.epam.informationhandling.service.interpreter;

import com.epam.informationhandling.exception.InformationHandlingException;

public interface AbstractExpression {
    void interpret(Context context) throws InformationHandlingException;
}
