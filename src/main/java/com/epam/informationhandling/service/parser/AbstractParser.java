package com.epam.informationhandling.service.parser;

public abstract class AbstractParser implements Parser {
    private final Parser successor;

    protected AbstractParser(Parser successor) {
        this.successor = successor;
    }

    protected Parser getSuccessor() {
        return successor;
    }
}
