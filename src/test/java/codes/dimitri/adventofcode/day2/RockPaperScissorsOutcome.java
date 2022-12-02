package codes.dimitri.adventofcode.day2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum RockPaperScissorsOutcome {
    WIN('Z',6),
    DRAW('Y',3),
    LOSE('X',0);

    private final char input;
    @Getter
    private final int outcomeScore;

    public static RockPaperScissorsOutcome fromInput(char input) {
        return Arrays
            .stream(values())
            .filter(outcome -> outcome.input == input)
            .findAny()
            .orElseThrow(UnsupportedOperationException::new);
    }
}
