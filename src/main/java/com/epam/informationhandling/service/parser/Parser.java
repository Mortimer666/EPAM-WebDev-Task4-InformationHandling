package com.epam.informationhandling.service.parser;

import com.epam.informationhandling.entity.TextComponent;

public interface Parser {
    TextComponent parse(String text);
}
