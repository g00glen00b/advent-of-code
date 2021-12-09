package be.g00glen00b.adventofcode.day9;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

@Value
public class Area {
    int[][] area;

    public static Area fromLines(List<String> lines) {
        int[][] area = lines
            .stream()
            .map(line -> line
                .chars()
                .map(character -> character - '0')
                .toArray())
            .toArray(int[][]::new);
        return new Area(area);
    }

    public List<Coordinate> calculateAdjacentCoordinates(Coordinate coordinate) {
        List<Coordinate> result = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();
        if (y != 0) result.add(calculateCoordinate(x, y - 1));
        if (y < area.length - 1) result.add(calculateCoordinate(x, y + 1));
        if (x != 0) result.add(calculateCoordinate(x - 1, y));
        if (x < area[y].length - 1) result.add(calculateCoordinate(x + 1, y));
        return result;
    }

    public boolean isLowPoint(Coordinate coordinate) {
        return calculateAdjacentCoordinates(coordinate)
            .stream()
            .allMatch(adjacent -> adjacent.isHigherThan(coordinate));
    }

    public List<Coordinate> calculateLowPoints() {
        return calculateCoordinates()
            .stream()
            .filter(this::isLowPoint)
            .collect(toList());
    }

    public int calculateRisk() {
        return calculateLowPoints()
            .stream()
            .mapToInt(Coordinate::getHeight)
            .map(height -> height + 1)
            .sum();
    }

    public Coordinate calculateCoordinate(int x, int y) {
        return new Coordinate(x, y, area[y][x]);
    }

    public List<Coordinate> calculateCoordinates() {
        return IntStream
            .range(0, area.length)
            .boxed()
            .flatMap(y -> IntStream
                .range(0, area[y].length)
                .mapToObj(x -> calculateCoordinate(x, y)))
            .collect(toList());
    }

    public List<Basin> calculateBasins() {
        return calculateLowPoints()
            .stream()
            .map(Basin::fromLowPoint)
            .map(this::increase)
            .collect(toList());
    }

    private Basin increase(Basin basin) {
        List<Coordinate> newEdge = basin
            .getEdge()
            .stream()
            .map(this::calculateAdjacentCoordinates)
            .flatMap(Collection::stream)
            .distinct()
            .filter(Coordinate::isBasin)
            .filter(not(basin::contains))
            .collect(toList());
        if (newEdge.isEmpty()) {
            return basin;
        } else {
            return increase(basin.withEdge(newEdge));
        }
    }
}
