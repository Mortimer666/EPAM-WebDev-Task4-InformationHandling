package com.epam.informationhandling.service.parser.impl;

import com.epam.informationhandling.entity.TextComponent;
import com.epam.informationhandling.entity.impl.TextComposite;
import com.epam.informationhandling.service.parser.AbstractParser;
import com.epam.informationhandling.service.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends AbstractParser {
    public static final Logger LOGGER = LogManager.getLogger(TextParser.class);
    private static final String DELIMITER = "\n";

    public TextParser(Parser successor) {
        super(successor);
        LOGGER.info("Text parser created");
    }

    @Override
    public TextComponent parse(String text) {
        TextComposite composite = new TextComposite();
        String[] parts = text.split(DELIMITER);

        for (String part : parts) {
            TextComponent paragraph = getSuccessor().parse(part);
            composite.add(paragraph);
        }
        return composite;
    }
}
