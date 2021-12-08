package be.g00glen00b.adventofcode.day6;

import lombok.Value;

import java.util.Arrays;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@Value
public class LanternfishPopulation {
    Map<Integer, Long> populationPerAge;

    public static LanternfishPopulation fromLine(String line) {
        Map<Integer, Long> populationPerAge = Arrays
            .stream(line.split(","))
            .map(Integer::parseInt)
            .collect(groupingBy(identity(), counting()));
        return new LanternfishPopulation(populationPerAge);
    }

    public LanternfishPopulation nextDay() {
        Map<Integer, Long> newPopulationsPerAge = populationPerAge
            .entrySet()
            .stream()
            .filter(entry -> entry.getKey() != 0)
            .collect(toMap(entry -> entry.getKey() - 1, Map.Entry::getValue));
        Long pregnantLanternfish = populationPerAge.getOrDefault(0, 0L);
        newPopulationsPerAge.put(8, pregnantLanternfish);
        newPopulationsPerAge.put(6, newPopulationsPerAge.getOrDefault(6, 0L) + pregnantLanternfish);
        return new LanternfishPopulation(newPopulationsPerAge);
    }

    public long calculatePopulation() {
        return populationPerAge
            .values()
            .stream()
            .mapToLong(Long::longValue)
            .sum();
    }
}
