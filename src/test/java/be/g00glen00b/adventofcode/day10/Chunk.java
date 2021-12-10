package be.g00glen00b.adventofcode.day10;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum Chunk {
    ANGLE('<', '>', 4), ROUND('(', ')', 1), SQUARE('[', ']', 2), CURLY('{', '}', 3);

    private final char openingDelimiter;
    private final char closingDelimiter;
    private final int completionScore;

    public boolean isOpeningDelimiter(char character) {
        return character == openingDelimiter;
    }

    public boolean isClosingDelimiter(char character) {
        return character == closingDelimiter;
    }

    public static Optional<Chunk> fromOpeningDelimiter(char character) {
        return Arrays
            .stream(values())
            .filter(chunk -> chunk.isOpeningDelimiter(character))
            .findAny();
    }

}
