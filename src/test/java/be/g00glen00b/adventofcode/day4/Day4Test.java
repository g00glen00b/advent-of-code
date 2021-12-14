package be.g00glen00b.adventofcode.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static be.g00glen00b.adventofcode.TestUtils.streamNumbers;
import static be.g00glen00b.adventofcode.TestUtils.unsupported;
import static org.assertj.core.api.Assertions.assertThat;

public class Day4Test {
    @ParameterizedTest
    @CsvSource({
        "/day4_sample.txt,4512",
        "/day4_full.txt,45031"
    })
    void part1(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        String numbers = lines.remove(0);
        BingoBoards result = streamNumbers(numbers)
            .reduce(BingoBoards.fromLines(lines), BingoBoards::mark, unsupported());
        assertThat(result
            .findWinner()
            .map(BingoBoard::calculateScore)
            .map(score -> score * result.getLastValue()))
            .isNotEmpty()
            .contains(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day4_sample.txt,1924",
        "/day4_full.txt,2568"
    })
    void part2(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        String numbers = lines.remove(0);
        LastWinnerBingoBoards result = streamNumbers(numbers)
            .reduce(LastWinnerBingoBoards.fromLines(lines), LastWinnerBingoBoards::mark, unsupported());
        assertThat(result
            .getLastWinner()
            .calculateScore() * result.getLastValue())
            .isEqualTo(expectedOutput);
    }
}
