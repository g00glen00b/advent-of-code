package codes.dimitri.adventofcode.day6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day6Test {
    @ParameterizedTest
    @CsvSource({
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
        "nppdvjthqldpwncqszvftbrmjlhg,6",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"
    })
    void partOne_examples(String buffer, int expectedResult) {
        List<DataStreamChunk> chunks = DataStreamChunk.fromDataStreamBuffer(buffer, 4);
        Integer result = chunks
            .stream()
            .filter(DataStreamChunk::isStartSignal)
            .findAny()
            .map(DataStreamChunk::position)
            .orElseThrow();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void partOne_full() throws URISyntaxException, IOException {
        List<String> lines = readLines("/day6/full.txt");
        String buffer = lines.get(0);
        List<DataStreamChunk> chunks = DataStreamChunk.fromDataStreamBuffer(buffer, 4);
        Integer result = chunks
            .stream()
            .filter(DataStreamChunk::isStartSignal)
            .findAny()
            .map(DataStreamChunk::position)
            .orElseThrow();
        assertThat(result).isEqualTo(1042);
    }

    @ParameterizedTest
    @CsvSource({
        "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
        "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
        "nppdvjthqldpwncqszvftbrmjlhg,23",
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"
    })
    void partTwo_examples(String buffer, int expectedResult) {
        List<DataStreamChunk> chunks = DataStreamChunk.fromDataStreamBuffer(buffer, 14);
        Integer result = chunks
            .stream()
            .filter(DataStreamChunk::isStartSignal)
            .findAny()
            .map(DataStreamChunk::position)
            .orElseThrow();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void partTwo_full() throws URISyntaxException, IOException {
        List<String> lines = readLines("/day6/full.txt");
        String buffer = lines.get(0);
        List<DataStreamChunk> chunks = DataStreamChunk.fromDataStreamBuffer(buffer, 14);
        Integer result = chunks
            .stream()
            .filter(DataStreamChunk::isStartSignal)
            .findAny()
            .map(DataStreamChunk::position)
            .orElseThrow();
        assertThat(result).isEqualTo(2980);
    }
}
