package com.epam.informationhandling.service.parser.impl;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.Lexeme;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.AbstractParser;
import com.epam.informationhandling.service.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    public static final Logger LOGGER = LogManager.getLogger(SentenceParser.class);
    private static final String LEAF_REGEX = "\\[[^]]*]|[^\\s\\[\\]]+\\n?";
    private static final String EXPRESSION_REGEX = "\\[.+]";


    public SentenceParser(Parser successor) {
        super(successor);
        LOGGER.info("Sentence parser created");
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite composite = new TextComposite();
        Pattern pattern = Pattern.compile(LEAF_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String value = matcher.group();
            Lexeme lexeme;
            if (value.matches(EXPRESSION_REGEX)) {
                lexeme = Lexeme.expression(value);
            } else {
                lexeme = Lexeme.word(value);
            }
            composite.add(lexeme);
        }
        return composite;
    }
}
