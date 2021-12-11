package be.g00glen00b.adventofcode.day11;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Value
public class OctopusField {
    Octopus[][] field;

    public void nextStep() {
        increaseEnergyLevel();
        reset();
    }

    public boolean isAllZeroEnergyLevel() {
        return streamOctopi()
            .allMatch(Octopus::isZeroEnergyLevel);
    }

    public void increaseEnergyLevel() {
        streamOctopi()
            .forEach(Octopus::increaseEnergyLevel);
    }

    private Stream<Octopus> streamOctopi() {
        return Arrays
            .stream(field)
            .flatMap(Arrays::stream);
    }

    public void reset() {
        streamOctopi()
            .forEach(Octopus::reset);
    }

    public int calculateFlashes() {
        return streamOctopi()
            .mapToInt(Octopus::getFlashCount)
            .sum();
    }

    public static OctopusField fromLines(List<String> lines) {
        Octopus[][] field = lines
            .stream()
            .map(line -> line
                .chars()
                .map(energyLevel -> energyLevel - '0')
                .mapToObj(Octopus::new)
                .toArray(Octopus[]::new))
            .toArray(Octopus[][]::new);
        IntStream
            .range(0, field.length)
            .forEach(y -> IntStream
                .range(0, field[y].length)
                .forEach(x -> field[y][x].setAdjacent(calculateAdjacent(field, x, y))));
        return new OctopusField(field);
    }

    private static Optional<Octopus> findByCoordinate(Octopus[][] octopi, int x, int y) {
        return x < 0 || y < 0 || y >= octopi.length || x >= octopi[y].length ? Optional.empty() : Optional.of(octopi[y][x]);
    }

    private static List<Octopus> calculateAdjacent(Octopus[][] octopi, int x, int y) {
        return Stream.of(
            findByCoordinate(octopi, x - 1, y - 1),
            findByCoordinate(octopi, x - 1, y),
            findByCoordinate(octopi, x - 1, y + 1),
            findByCoordinate(octopi, x, y - 1),
            findByCoordinate(octopi, x, y + 1),
            findByCoordinate(octopi, x + 1, y - 1),
            findByCoordinate(octopi, x + 1, y),
            findByCoordinate(octopi, x + 1, y + 1))
            .flatMap(Optional::stream)
            .collect(toList());
    }
}
