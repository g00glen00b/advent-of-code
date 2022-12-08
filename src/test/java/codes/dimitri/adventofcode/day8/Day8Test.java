package codes.dimitri.adventofcode.day8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static codes.dimitri.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day8Test {
    @ParameterizedTest
    @CsvSource({
        "/day8/sample.txt,21",
        "/day8/full.txt,21"
    })
    void partOne(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        TreeGrid treeGrid = TreeGrid.fromLines(lines);
        assertThat(treeGrid.countVisibleTrees()).isEqualTo(expectedResult);
    }

    @Test
    void calculateVisibleTrees() throws URISyntaxException, IOException {
        List<String> lines = readLines("/day8/sample.txt");
        TreeGrid treeGrid = TreeGrid.fromLines(lines);
        Coordinates coordinates = new Coordinates(2, 3);
        assertThat(treeGrid.calculateVisibleTrees(coordinates, Vector.UP_ONE)).isEqualTo(2);
        assertThat(treeGrid.calculateVisibleTrees(coordinates, Vector.LEFT_ONE)).isEqualTo(2);
        assertThat(treeGrid.calculateVisibleTrees(coordinates, Vector.DOWN_ONE)).isEqualTo(1);
        assertThat(treeGrid.calculateVisibleTrees(coordinates, Vector.RIGHT_ONE)).isEqualTo(2);
    }

    @Test
    void calculateScenicScore() throws URISyntaxException, IOException {
        List<String> lines = readLines("/day8/sample.txt");
        TreeGrid treeGrid = TreeGrid.fromLines(lines);
        assertThat(treeGrid.calculateScenicScore(new Coordinates(2, 3))).isEqualTo(8);
    }

    @ParameterizedTest
    @CsvSource({
        "/day8/sample.txt,8",
        "/day8/full.txt,334880"
    })
    void partTwo(String fileLocation, int expectedResult) throws URISyntaxException, IOException {
        List<String> lines = readLines(fileLocation);
        TreeGrid treeGrid = TreeGrid.fromLines(lines);
        assertThat(treeGrid.calculateHighestScenicScore()).isEqualTo(expectedResult);
    }
}
