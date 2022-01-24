package com.epam.informationhandling.service;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.exception.InformationHandlingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TextLogicTest {
    private static final TextLogic textLogic = new TextLogic();
    private static final Lexeme FIRST_LEXEME = Lexeme.word("File");
    private static final Lexeme SECOND_LEXEME = Lexeme.expression("[2 x *]");
    private static final Lexeme THIRD_LEXEME = Lexeme.word("Megabytes.");
    private static final Lexeme CALCULATED_EXPRESSION = Lexeme.word("4.0");

    @Test
    void testRestoreShouldReturnCorrectTextWhenCompositeIsValid() {
        //given
        String expected = "File [2 x *] Megabytes.";
        TextComponent composite = createTextComponent();
        //when
        String actual = textLogic.restore(composite);
        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCalculateExpressionsShouldCalculateCorrectlyWhenCompositeIsValid() throws InformationHandlingException {
        //given
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("x", 2.0);
        parameters.put("y", 3.0);
        TextComponent composite = createTextComponent();
        //when
        TextComponent actual = textLogic.calculateExpressions(composite, parameters);
        //then
        TextComponent expected = createExpectedTextCompositeToCalculateExpressionsTest();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSortLexemesInSentencesShouldSortByLengthWhenCompositeIsValid() {
        //given
        TextComponent composite = createUnsortedTextComposite();
        //when
        TextComponent actual = textLogic.sortWordsInSentencesByLength(composite);
        //then
        TextComponent expected = createSortedTextCompositeToSortLexemesTest();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testSortParagraphsShouldSortByAmountOfSentencesWhenCompositeIsValid() {
        //given
        TextComponent composite = createUnsortedTextCompositeToSortParagraphsTest();
        //when
        TextComponent actual = textLogic.sortParagraphsBySentenceAmount(composite);
        //then
        TextComponent expected = createSortedTextCompositeToSortParagraphsTest();
        Assertions.assertEquals(expected, actual);
    }

    private TextComponent createTextComponent() {
        List<TextComponent> lexemes = Arrays.asList(FIRST_LEXEME, SECOND_LEXEME, THIRD_LEXEME);
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite paragraph = new TextComposite();
        paragraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(paragraph);
        return text;
    }

    private TextComposite createExpectedTextCompositeToCalculateExpressionsTest() {
        List<Lexeme> lexemes = Arrays.asList(FIRST_LEXEME, CALCULATED_EXPRESSION, THIRD_LEXEME);
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite paragraph = new TextComposite();
        paragraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(paragraph);
        return text;
    }

    private TextComposite createUnsortedTextComposite() {
        List<Lexeme> lexemes = Arrays.asList(Lexeme.word("Longest"), Lexeme.word("short"), Lexeme.word("longer"));
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite paragraph = new TextComposite();
        paragraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(paragraph);
        return text;
    }

    private TextComposite createSortedTextCompositeToSortLexemesTest() {
        List<Lexeme> lexemes = Arrays.asList(Lexeme.word("short"), Lexeme.word("longer"), Lexeme.word("Longest"));
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite paragraph = new TextComposite();
        paragraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(paragraph);
        return text;
    }

    private TextComposite createUnsortedTextCompositeToSortParagraphsTest() {
        List<Lexeme> lexemes = Arrays.asList(FIRST_LEXEME, SECOND_LEXEME, THIRD_LEXEME);
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite firstParagraph = new TextComposite();
        firstParagraph.add(sentence);
        firstParagraph.add(sentence);
        TextComposite secondParagraph = new TextComposite();
        secondParagraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(firstParagraph);
        text.add(secondParagraph);
        return text;
    }

    private TextComposite createSortedTextCompositeToSortParagraphsTest() {
        List<Lexeme> lexemes = Arrays.asList(FIRST_LEXEME, SECOND_LEXEME, THIRD_LEXEME);
        TextComposite sentence = new TextComposite(lexemes);
        TextComposite firstParagraph = new TextComposite();
        firstParagraph.add(sentence);
        firstParagraph.add(sentence);
        TextComposite secondParagraph = new TextComposite();
        secondParagraph.add(sentence);
        TextComposite text = new TextComposite();
        text.add(secondParagraph);
        text.add(firstParagraph);
        return text;
    }
}
