package be.g00glen00b.adventofcode.day4;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

@Value
public class BingoBoard {
    private static final int VALUES_PER_ROW = 5;
    List<BingoRow> rowsAndColumns;

    public static BingoBoard fromLines(List<String> lines) {
        int[][] values = new int[VALUES_PER_ROW][VALUES_PER_ROW];
        for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
            values[rowIndex] = Arrays
                .stream(lines.get(rowIndex).split("\\s+"))
                .filter(not(String::isBlank))
                .mapToInt(Integer::parseInt)
                .toArray();
        }
        Stream<BingoRow> streamOfRows = Arrays
            .stream(values)
            .map(row -> Arrays.stream(row).boxed().collect(toList()))
            .map(BingoRow::new);
        Stream<BingoRow> streamOfColumns = IntStream
            .range(0, VALUES_PER_ROW)
            .mapToObj(columnIndex -> IntStream
                .range(0, VALUES_PER_ROW)
                .mapToObj(rowIndex -> values[rowIndex][columnIndex])
                .collect(toList()))
            .map(BingoRow::new);
        return new BingoBoard(Stream
            .concat(streamOfRows, streamOfColumns)
            .collect(toList()));
    }

    public BingoBoard mark(int number) {
        List<BingoRow> rowsAndColumns = this.rowsAndColumns
            .stream()
            .map(row -> row.mark(number))
            .collect(toList());
        return new BingoBoard(rowsAndColumns);
    }

    public boolean isComplete() {
        return rowsAndColumns
            .stream()
            .anyMatch(BingoRow::isComplete);
    }

    public int calculateScore() {
        return rowsAndColumns
            .stream()
            .map(BingoRow::getNumbers)
            .flatMap(Collection::stream)
            .distinct()
            .mapToInt(Integer::intValue)
            .sum();
    }
}
