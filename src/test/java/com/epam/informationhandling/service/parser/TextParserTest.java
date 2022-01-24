package com.epam.informationhandling.service.parser;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.impl.ParagraphParser;
import com.epam.informationhandling.service.parser.impl.TextParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TextParserTest {
    private static final String VALID_TEXT = "First paragraph! Still first.\nSecond Paragraph.";
    private static final String FIRST_PARAGRAPH = "First paragraph! Still first.";
    private static final String SECOND_PARAGRAPH = "Second Paragraph.";
    private static final TextComponent FIRST_COMPONENT = Lexeme.word(FIRST_PARAGRAPH);
    private static final TextComponent SECOND_COMPONENT = Lexeme.word(SECOND_PARAGRAPH);

    @Test
    void testParseShouldReturnCorrectComponentWhenTextIsValid() {
        //given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        Mockito.when(paragraphParser.parse(FIRST_PARAGRAPH)).thenReturn(FIRST_COMPONENT);
        Mockito.when(paragraphParser.parse(SECOND_PARAGRAPH)).thenReturn(SECOND_COMPONENT);
        TextParser textParser = new TextParser(paragraphParser);
        TextComponent expected = new TextComposite();
        expected.add(FIRST_COMPONENT);
        expected.add(SECOND_COMPONENT);
        //when
        TextComponent actual = textParser.parse(VALID_TEXT);
        //then
        Assertions.assertEquals(expected, actual);
    }
}
