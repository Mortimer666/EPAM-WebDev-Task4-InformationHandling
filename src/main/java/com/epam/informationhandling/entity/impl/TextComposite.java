package com.epam.informationhandling.entity.impl;

import com.epam.informationhandling.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final String SPACE = " ";
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite() {
    }

    public TextComposite(List<? extends TextComponent> components) {
        this.components.addAll(components);
    }

    @Override
    public List<TextComponent> getComponents() {
        return new ArrayList<>(components);
    }

    @Override
    public void add(TextComponent textComponent) {
        components.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        components.remove(textComponent);
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return components.equals(that.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + (components.isEmpty() ? 0 : components.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : components) {
            stringBuilder.append(component);
            stringBuilder.append(SPACE);
        }
        return stringBuilder.toString().trim();
    }
}
