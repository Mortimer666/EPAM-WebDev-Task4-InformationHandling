package com.epam.informationhandling.entity.impl;

import com.epam.informationhandling.entity.LexemeType;
import com.epam.informationhandling.entity.TextComponent;

import java.util.List;

public class Lexeme implements TextComponent {
    private final String value;
    private final LexemeType type;
    private static final String MESSAGE = "Can't use operation on leaf.";

    private Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException(MESSAGE);
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException(MESSAGE);
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException(MESSAGE);
    }

    @Override
    public int size() {
        return value.length();
    }

    public LexemeType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lexeme lexeme = (Lexeme) o;
        return value.equals(lexeme.value) && type == lexeme.type;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = type != null ? type.hashCode() : 0;
        result = prime * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
