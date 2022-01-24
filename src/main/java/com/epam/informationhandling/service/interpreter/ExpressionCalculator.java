package com.epam.informationhandling.service.interpreter;

import com.epam.informationhandling.exception.InformationHandlingException;
import com.epam.informationhandling.service.interpreter.expressions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {
    private static final String EXPRESSION_SEPARATOR = "\\s+";
    private static final String BRACKETS_REGEX = "[\\[\\]]";
    private static final String EMPTY_STRING = "";

    public double calculate(String expression, Map<String, Double> parameters) throws InformationHandlingException {
        List<AbstractExpression> expressions = parse(expression, parameters);
        Context context = new Context();
        for (AbstractExpression terminal : expressions) {
            terminal.interpret(context);
        }
        return context.popValue();
    }

    private List<AbstractExpression> parse(String expression, Map<String, Double> parameters)
            throws InformationHandlingException {
        List<AbstractExpression> expressions = new ArrayList<>();
        String expressionValue = removeBrackets(expression);

        for (String part : expressionValue.split(EXPRESSION_SEPARATOR)) {
            if (!part.isEmpty()) {
                switch (part) {
                    case "+":
                        expressions.add(new TerminalExpressionAdd());
                        break;
                    case "-":
                        expressions.add(new TerminalExpressionSubtract());
                        break;
                    case "*":
                        expressions.add(new TerminalExpressionMultiply());
                        break;
                    case "/":
                        expressions.add(new TerminalExpressionDivide());
                        break;
                    default:
                        try (Scanner scanner = new Scanner(part)) {
                            if (scanner.hasNextDouble()) {
                                Double number = scanner.nextDouble();
                                expressions.add(new NonTerminalExpression(number));
                            } else {
                                String key = scanner.next();
                                if (parameters.containsKey(key)) {
                                    Double number = parameters.get(key);
                                    expressions.add(new NonTerminalExpression(number));
                                } else {
                                    throw new InformationHandlingException
                                            ("There is unknown variable in expression: " + key);
                                }
                            }
                        }
                }
            }
        }
        return expressions;
    }

    private String removeBrackets(String expression) {
        return expression.replaceAll(BRACKETS_REGEX, EMPTY_STRING);
    }
}
