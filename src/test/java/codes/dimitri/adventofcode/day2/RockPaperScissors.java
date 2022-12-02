package codes.dimitri.adventofcode.day2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum RockPaperScissors {
    ROCK('A', 'X', 1),
    PAPER('B', 'Y', 2),
    SCISSORS('C', 'Z', 3);

    private final char elfInput;
    private final char ownInput;
    @Getter
    private final int choiceScore;

    public static RockPaperScissors fromElfInput(char elfInput) {
        return Arrays
            .stream(values())
            .filter(rockPaperScissors -> rockPaperScissors.elfInput == elfInput)
            .findAny()
            .orElseThrow(UnsupportedOperationException::new);
    }

    public static RockPaperScissors fromOwnInput(char ownInput) {
        return Arrays
            .stream(values())
            .filter(rockPaperScissors -> rockPaperScissors.ownInput == ownInput)
            .findAny()
            .orElseThrow(UnsupportedOperationException::new);
    }

    public RockPaperScissors calculateOpponent(RockPaperScissorsOutcome outcome) {
        return Arrays
            .stream(values())
            .filter(rockPaperScissors -> outcome.equals(rockPaperScissors.calculateOutcome(this)))
            .findAny()
            .orElseThrow(UnsupportedOperationException::new);
    }

    public RockPaperScissorsOutcome calculateOutcome(RockPaperScissors other) {
        if (this.isWinnerAgainst(other)) return RockPaperScissorsOutcome.WIN;
        if (other.isWinnerAgainst(this)) return RockPaperScissorsOutcome.LOSE;
        return RockPaperScissorsOutcome.DRAW;
    }

    public boolean isWinnerAgainst(RockPaperScissors other) {
        return switch (this) {
            case ROCK -> SCISSORS.equals(other);
            case SCISSORS -> PAPER.equals(other);
            case PAPER -> ROCK.equals(other);
        };
    }

    public int calculateScoreAgainst(RockPaperScissors other) {
        RockPaperScissorsOutcome outcome = calculateOutcome(other);
        return this.getChoiceScore() + outcome.getOutcomeScore();
    }
}
