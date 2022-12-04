package codes.dimitri.adventofcode.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static codes.dimitri.adventofcode.TestUtils.splitByNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class Day3Test {
    @ParameterizedTest
    @CsvSource({
        "/day3/sample.txt,157",
        "/day3/full.txt,7727"
    })
    void partOne(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> rucksacks = readLines(fileLocation);
        int result = rucksacks
            .stream()
            .map(Rucksack::ofString)
            .map(Rucksack::findOverlappingItems)
            .flatMap(Collection::stream)
            .mapToInt(Item::priority)
            .sum();
        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "/day3/sample.txt,70",
        "/day3/full.txt,2609"
    })
    void partTwo(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> rucksacks = readLines(fileLocation);
        List<List<String>> rucksackGroups = splitByNumber(rucksacks, 3);
        int result = rucksackGroups
            .stream()
            .map(group -> Badge.ofRucksacks(
                Rucksack.ofString(group.get(0)),
                Rucksack.ofString(group.get(1)),
                Rucksack.ofString(group.get(2))
            ))
            .flatMap(Optional::stream)
            .map(Badge::badgeItem)
            .mapToInt(Item::priority)
            .sum();
        assertThat(result).isEqualTo(expectedResult);
    }
}
