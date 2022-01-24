package com.epam.informationhandling.service;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.exception.InformationHandlingException;
import com.epam.informationhandling.service.builder.ParserChainBuilder;
import com.epam.informationhandling.service.interpreter.ExpressionCalculator;
import com.epam.informationhandling.service.parser.Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TextLogic {
    private static final String EXPRESSION_REGEX = "\\[.+]";
    private static final ExpressionCalculator calculator = new ExpressionCalculator();

    public TextComponent parse(String text) {
        ParserChainBuilder parserChainBuilder = new ParserChainBuilder();
        Parser parser = parserChainBuilder.build();
        return parser.parse(text);
    }

    public String restore(TextComponent composite) {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent component : composite.getComponents()) {
            stringBuilder.append("\n\n    ").append(component);
        }
        String text = stringBuilder.toString();
        return text.replaceFirst("\n{2}\\s{4}", "");
    }

    public TextComposite calculateExpressions(TextComponent text, Map<String, Double> parameters) throws InformationHandlingException {
        TextComposite newText = new TextComposite();
        for (TextComponent paragraph : text.getComponents()) {
            TextComponent newParagraph = new TextComposite();
            for (TextComponent sentence : paragraph.getComponents()) {
                List<TextComponent> lexemes = new ArrayList<>();
                for (TextComponent lexemeComponent : sentence.getComponents()) {
                    String value = lexemeComponent.toString();
                    if (value.matches(EXPRESSION_REGEX)) {
                        double calculatedValue = calculator.calculate(value, parameters);
                        String stringValue = Double.toString(calculatedValue);
                        TextComponent lexeme = Lexeme.word(stringValue);
                        lexemes.add(lexeme);
                    } else {
                        lexemes.add(lexemeComponent);
                    }
                }
                newParagraph.add(new TextComposite(lexemes));
            }
            newText.add(newParagraph);
        }
        return newText;
    }

    public TextComposite sortParagraphsBySentenceAmount(TextComponent text) {
        List<TextComponent> paragraphs = text.getComponents();
        Comparator<TextComponent> bySentenceAmount = Comparator.comparing(TextComponent::size);
        paragraphs.sort(bySentenceAmount);
        return new TextComposite(paragraphs);
    }

    public TextComposite sortWordsInSentencesByLength(TextComponent text) {
        Comparator<TextComponent> byLexemeLength = Comparator.comparing(TextComponent::size);
        TextComposite sortedText = new TextComposite();
        for (TextComponent paragraph : text.getComponents()) {
            TextComposite sortedParagraph = new TextComposite();
            for (TextComponent sentence : paragraph.getComponents()) {
                List<TextComponent> lexemes = sentence.getComponents();
                lexemes.sort(byLexemeLength);
                TextComposite sortedSentence = new TextComposite(lexemes);
                sortedParagraph.add(sortedSentence);
            }
            sortedText.add(sortedParagraph);
        }
        return sortedText;
    }
}
