package be.g00glen00b.adventofcode.day18;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

public class Day18Test {
    @Test
    void partOne_explode() {
        assertThat(SnailMath.reduce("[[[[[9,8],1],2],3],4]")).isEqualTo("[[[[0,9],2],3],4]");
        assertThat(SnailMath.reduce("[7,[6,[5,[4,[3,2]]]]]")).isEqualTo("[7,[6,[5,[7,0]]]]");
        assertThat(SnailMath.reduce("[[6,[5,[4,[3,2]]]],1]")).isEqualTo("[[6,[5,[7,0]]],3]");
        assertThat(SnailMath.reduce("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]")).isEqualTo("[[3,[2,[8,0]]],[9,[5,[7,0]]]]");
    }

    @Test
    void partOne_exampleOne() {
        assertThat(SnailMath.add("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]")).isEqualTo("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]");
    }

    @Test
    void partOne_examplePartial() {
        assertThat(SnailMath.add("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]", "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")).isEqualTo("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]");
    }

    @Test
    void partOne_multiple() throws URISyntaxException, IOException {
        List<String> values = readLines("/day18_sample1.txt");
        String result = values
            .stream()
            .reduce(SnailMath::add)
            .orElseThrow();
        assertThat(result).isEqualTo("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]");
    }

    @Test
    void partOne_magnitude() {
        assertThat(SnailMath.calculateMagnitudeNumeric("[[1,2],[[3,4],5]]")).isEqualTo(143);
        assertThat(SnailMath.calculateMagnitudeNumeric("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")).isEqualTo(1384);
        assertThat(SnailMath.calculateMagnitudeNumeric("[[[[1,1],[2,2]],[3,3]],[4,4]]")).isEqualTo(445);
        assertThat(SnailMath.calculateMagnitudeNumeric("[[[[3,0],[5,3]],[4,4]],[5,5]]")).isEqualTo(791);
        assertThat(SnailMath.calculateMagnitudeNumeric("[[[[5,0],[7,4]],[5,5]],[6,6]]")).isEqualTo(1137);
        assertThat(SnailMath.calculateMagnitudeNumeric("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")).isEqualTo(3488);
    }

    @ParameterizedTest
    @CsvSource({
        "/day18_sample2.txt,4140",
        "/day18_full.txt,0"
    })
    void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> values = readLines(fileLocation);
        String result = values
            .stream()
            .reduce(SnailMath::add)
            .orElseThrow();
        assertThat(SnailMath.calculateMagnitudeNumeric(result)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day18_sample2.txt,3993",
        "/day18_full.txt,4735"
    })
    void partTwo(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        List<String> values = readLines(fileLocation);
        int maxMagnitude = values
            .stream()
            .flatMap(value1 -> values
                .stream()
                .filter(not(value1::equals))
                .map(value2 -> SnailMath.add(value1, value2)))
            .mapToInt(SnailMath::calculateMagnitudeNumeric)
            .max()
            .orElseThrow();
        assertThat(maxMagnitude).isEqualTo(expectedOutput);
    }
}
