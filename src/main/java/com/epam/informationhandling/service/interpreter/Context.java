package com.epam.informationhandling.service.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {
    private final Deque<Double> contextValues = new ArrayDeque<>();

    public Double popValue() {
        return contextValues.pop();
    }

    public void pushValue(Double value) {
        contextValues.push(value);
    }
}
