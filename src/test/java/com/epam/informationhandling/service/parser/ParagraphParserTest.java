package com.epam.informationhandling.service.parser;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.impl.ParagraphParser;
import com.epam.informationhandling.service.parser.impl.SentenceParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ParagraphParserTest {
    private static final String VALID_PARAGRAPH = "First sentence. Second sentence!";
    private static final String FIRST_SENTENCE = "First sentence.";
    private static final String SECOND_SENTENCE = "Second sentence!";
    private static final TextComponent FIRST_COMPONENT = Lexeme.word(FIRST_SENTENCE);
    private static final TextComponent SECOND_COMPONENT = Lexeme.word(SECOND_SENTENCE);

    @Test
    void testParsShouldReturnCorrectComponentWhenParagraphIsValid() {
        //given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
        Mockito.when(sentenceParser.parse(FIRST_SENTENCE)).thenReturn(FIRST_COMPONENT);
        Mockito.when(sentenceParser.parse(SECOND_SENTENCE)).thenReturn(SECOND_COMPONENT);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        TextComponent expected = new TextComposite();
        expected.add(FIRST_COMPONENT);
        expected.add(SECOND_COMPONENT);
        //when
        TextComponent actual = paragraphParser.parse(VALID_PARAGRAPH);
        //then
        Assertions.assertEquals(expected, actual);
    }
}
