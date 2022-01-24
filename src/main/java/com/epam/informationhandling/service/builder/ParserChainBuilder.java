package com.epam.informationhandling.service.builder;

import com.epam.informationhandling.service.parser.Parser;
import com.epam.informationhandling.service.parser.impl.ParagraphParser;
import com.epam.informationhandling.service.parser.impl.SentenceParser;
import com.epam.informationhandling.service.parser.impl.TextParser;

public class ParserChainBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(new SentenceParser(null)));
    }
}
