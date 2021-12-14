package be.g00glen00b.adventofcode.day13;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class Day13Test {
    @ParameterizedTest
    @CsvSource({
        "/day13_sample.txt,17",
        "/day13_full.txt,661"
    })
    public void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> parts = splitLinesByEmptyLine(lines);
        List<String> paperMarkCoordinates = parts.get(0);
        List<String> foldInstructions = parts.get(1);
        TransparentPaper paper = TransparentPaper.fromLines(paperMarkCoordinates);
        FoldInstruction instruction = FoldInstruction.fromLine(foldInstructions.get(0));
        TransparentPaper result = paper.fold(instruction);
        assertThat(result.calculateMarkCount()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day13_sample.txt,16",
        "/day13_full.txt,89"
    })
    public void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        List<List<String>> parts = splitLinesByEmptyLine(lines);
        List<String> paperMarkCoordinates = parts.get(0);
        List<String> foldInstructions = parts.get(1);
        TransparentPaper result = foldInstructions
            .stream()
            .map(FoldInstruction::fromLine)
            .reduce(TransparentPaper.fromLines(paperMarkCoordinates), TransparentPaper::fold, unsupported());
        result.print();
        assertThat(result.calculateMarkCount()).isEqualTo(expectedOutput);
    }
}
