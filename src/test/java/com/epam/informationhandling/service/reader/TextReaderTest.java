package com.epam.informationhandling.service.reader;

import com.epam.informationhandling.exception.InformationHandlingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextReaderTest {
    private static final String VALID_FILE_PATH = "src/main/resources/Text.txt";
    private static final String INVALID_FILE_PATH = "invalidFilePath";
    private static final String NULL_FILE_PATH = null;
    private static final String EMPTY_FILE_PATH = "";
    private static final String EXPECTED = "It has survived - not only (five) centuries, but also the leap into [13  2 +] electronic typesetting, remaining [3  5 +] essentially [15  3 /] unchanged. It was popularised in the [5 x *] with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
            "\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using [2 3 * y +] Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here, making it look like readable English." +
            "\nIt is a [1200  5 /] established fact that a reader will be of a page when looking at its layout." +
            "\nBye.";

    @Test
    void testReadShouldReturnCorrectTextWhenFileIsValid() throws InformationHandlingException {
        //given
        TextReader textReader = new TextReader();
        //when
        String actual = textReader.read(VALID_FILE_PATH);
        //then
        Assertions.assertEquals(EXPECTED, actual);
    }

    @Test
    void testReadShouldThrowExceptionWhenFilePathIsNull() {
        //given
        TextReader textReader = new TextReader();
        //when
        InformationHandlingException thrown = Assertions.assertThrows(InformationHandlingException.class,
                () -> textReader.read(NULL_FILE_PATH));
        //then
        Assertions.assertEquals("Filepath is empty or null.", thrown.getMessage());
    }

    @Test
    void testReadShouldThrowExceptionWhenFilePathIsEmpty() {
        //given
        TextReader textReader = new TextReader();
        //when
        InformationHandlingException thrown = Assertions.assertThrows(InformationHandlingException.class,
                () -> textReader.read(EMPTY_FILE_PATH));
        //then
        Assertions.assertEquals("Filepath is empty or null.", thrown.getMessage());
    }

    @Test
    void testReadShouldThrowExceptionWhenFilePathIsInvalid() {
        //given
        TextReader textReader = new TextReader();
        //when
        InformationHandlingException thrown = Assertions.assertThrows(InformationHandlingException.class,
                () -> textReader.read(INVALID_FILE_PATH));
        //then
        Assertions.assertEquals("Can't open file cause filepath is invalid", thrown.getMessage());
    }
}
