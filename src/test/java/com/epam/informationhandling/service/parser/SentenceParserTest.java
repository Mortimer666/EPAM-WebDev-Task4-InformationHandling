package com.epam.informationhandling.service.parser;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.impl.SentenceParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SentenceParserTest {
    private static final String VALID_SENTENCE = "[10 x /] word!";
    private static final String Expression = "[10 x /]";
    private static final String WORD = "word!";
    private static final TextComponent FIRST_COMPONENT = Lexeme.expression(Expression);
    private static final TextComponent SECOND_COMPONENT = Lexeme.word(WORD);

    @Test
    void testParseShouldReturnCorrectComponentWhenSentenceIsValid() {
        //given
        SentenceParser sentenceParser = new SentenceParser(null);
        TextComponent expected = new TextComposite();
        expected.add(FIRST_COMPONENT);
        expected.add(SECOND_COMPONENT);
        //when
        TextComponent actual = sentenceParser.parse(VALID_SENTENCE);
        //then
        Assertions.assertEquals(expected, actual);
    }
}
