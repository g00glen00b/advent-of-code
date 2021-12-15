package be.g00glen00b.adventofcode.day15;

import lombok.Value;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.function.Function.identity;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toMap;

@Value
public class SmallCave {
    Map<Coordinates, Integer> riskLevels;

    public Coordinates calculateTopLeftCoordinates() {
        return new Coordinates(0, 0);
    }

    public Coordinates calculateBottomRightCoordinates() {
        return riskLevels
            .keySet()
            .stream()
            .max(comparingInt(Coordinates::calculateXYProduct))
            .orElseThrow();
    }

    public int calculateTotalDistance(Coordinates start, Coordinates end) {
        PriorityQueue<TraversalRisk> lowestRiskQueue = new PriorityQueue<>(comparingInt(TraversalRisk::getTotalRisk));
        HashSet<Coordinates> settledCoordinates = new HashSet<>();
        lowestRiskQueue.add(new TraversalRisk(start, 0));
        while (!lowestRiskQueue.isEmpty() && !lowestRiskQueue.peek().getCoordinates().equals(end)) {
            TraversalRisk lowestRisk = lowestRiskQueue.poll();
            lowestRisk
                .getCoordinates()
                .calculateAdjacent()
                .stream()
                .filter(riskLevels::containsKey)
                .filter(not(settledCoordinates::contains))
                .peek(settledCoordinates::add)
                .map(adjacentCoordinates -> new TraversalRisk(adjacentCoordinates, lowestRisk.getTotalRisk() + riskLevels.get(adjacentCoordinates)))
                .forEach(lowestRiskQueue::add);
        }
        return lowestRiskQueue.peek().getTotalRisk();
    }

    public SmallCave grow() {
        HashMap<Coordinates, Integer> newRiskLevels = new HashMap<>();
        Coordinates bottomRightCoordinates = calculateBottomRightCoordinates();
        int width = bottomRightCoordinates.getX() + 1;
        int height = bottomRightCoordinates.getY() + 1;
        riskLevels
            .keySet()
            .forEach(coordinates -> newRiskLevels.putAll(calculateSubRiskLevels(coordinates, width, height)));
        return new SmallCave(newRiskLevels);
    }

    private Map<Coordinates, Integer> calculateSubRiskLevels(Coordinates coordinates, int offsetX, int offetY) {
        int x = coordinates.getX();
        int y = coordinates.getY();
        HashMap<Coordinates, Integer> results = new HashMap<>();
        for (int xRepitition = 0; xRepitition < 5; xRepitition++) {
            for (int yRepitition = 0; yRepitition < 5; yRepitition++) {
                int newX = x + (offsetX * xRepitition);
                int newY = y + (offetY * yRepitition);
                results.put(new Coordinates(newX, newY), calculateNewRisk(riskLevels.get(coordinates), xRepitition, yRepitition));
            }
        }
        return results;
    }

    private int calculateNewRisk(int originalRisk, int xRepitition, int yRepitition) {
        int newRisk = originalRisk + xRepitition + yRepitition;
        while (newRisk > 9) newRisk -= 9;
        return newRisk;
    }

    public static SmallCave fromLines(List<String> lines) {
        int[][] riskLevels = calculateRiskLevels(lines);
        Map<Coordinates, Integer> riskLevelsMap = calculateRiskLevelsMap(riskLevels);
        return new SmallCave(riskLevelsMap);
    }

    private static Map<Coordinates, Integer> calculateRiskLevelsMap(int[][] riskLevels) {
        return IntStream
            .range(0, riskLevels.length)
            .boxed()
            .flatMap(y -> IntStream
                .range(0, riskLevels[y].length)
                .mapToObj(x -> new Coordinates(x, y)))
            .collect(toMap(identity(), coordinates -> riskLevels[coordinates.getY()][coordinates.getX()]));
    }

    private static int[][] calculateRiskLevels(List<String> lines) {
        return lines
            .stream()
            .map(line -> line
                .chars()
                .map(value -> value - '0')
                .toArray())
            .toArray(int[][]::new);
    }
}
