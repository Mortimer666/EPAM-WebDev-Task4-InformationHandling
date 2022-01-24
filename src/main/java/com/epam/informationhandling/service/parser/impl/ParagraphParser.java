package com.epam.informationhandling.service.parser.impl;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.AbstractParser;
import com.epam.informationhandling.service.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    public static final Logger LOGGER = LogManager.getLogger(ParagraphParser.class);
    private static final String SENTENCE_REGEX = "[A-Z].+?[.!?]{1,3}";

    public ParagraphParser(Parser successor) {
        super(successor);
        LOGGER.info("Paragraph parser created");
    }

    @Override
    public TextComponent parse(String paragraph) {
        TextComposite composite = new TextComposite();
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(paragraph);

        while (matcher.find()) {
            String part = matcher.group().trim();
            TextComponent sentence = getSuccessor().parse(part);
            composite.add(sentence);
        }
        return composite;
    }
}
