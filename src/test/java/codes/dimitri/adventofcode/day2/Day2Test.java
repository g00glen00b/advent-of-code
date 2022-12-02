package codes.dimitri.adventofcode.day2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day2Test {
    @ParameterizedTest
    @CsvSource({
        "/day2/sample.txt,15",
        "/day2/full.txt,11666"
    })
    void partOne(String fileLocation, int expectedScore) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        int totalScore = calculatePartOneTotalScore(lines);
        assertThat(totalScore).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @CsvSource({
        "/day2/sample.txt,12",
        "/day2/full.txt,12767"
    })
    void partTwo(String fileLocation, int expectedScore) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        int totalScore = calculatePartTwoTotalScore(lines);
        assertThat(totalScore).isEqualTo(expectedScore);
    }

    private static int calculatePartOneScore(String input) {
        RockPaperScissors elf = RockPaperScissors.fromElfInput(input.charAt(0));
        RockPaperScissors own = RockPaperScissors.fromOwnInput(input.charAt(2));
        return own.calculateScoreAgainst(elf);
    }

    private static int calculatePartOneTotalScore(List<String> inputs) {
        return inputs
            .stream()
            .mapToInt(Day2Test::calculatePartOneScore)
            .sum();
    }

    private static int calculatePartTwoScore(String input) {
        RockPaperScissors elf = RockPaperScissors.fromElfInput(input.charAt(0));
        RockPaperScissorsOutcome expectedOutcome = RockPaperScissorsOutcome.fromInput(input.charAt(2));
        RockPaperScissors own = elf.calculateOpponent(expectedOutcome);
        return own.calculateScoreAgainst(elf);
    }

    private static int calculatePartTwoTotalScore(List<String> inputs) {
        return inputs
            .stream()
            .mapToInt(Day2Test::calculatePartTwoScore)
            .sum();
    }
}
