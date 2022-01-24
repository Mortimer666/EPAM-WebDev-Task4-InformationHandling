package com.epam.informationhandling.service.reader;

import com.epam.informationhandling.exception.InformationHandlingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextReader {
    public String read(String filepath) throws InformationHandlingException {
        if (filepath == null || filepath.isEmpty()) {
            throw new InformationHandlingException("Filepath is empty or null.");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Path path = Paths.get(filepath);
            List<String> lines = Files.readAllLines(path);
            lines.replaceAll(String::trim);
            lines.forEach(line -> stringBuilder.append(line).append("\n"));
        } catch (IOException e) {
            throw new InformationHandlingException("Can't open file cause filepath is invalid");
        }
        return stringBuilder.toString().trim();
    }
}
