package be.g00glen00b.adventofcode.day14;

import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.toList;

@Value
public class PolymerTemplate {
    List<PolymerPairCount> pairCounts;

    public PolymerTemplate(Map<PolymerPair, Long> counts) {
        this.pairCounts = counts
            .entrySet()
            .stream()
            .map(entry -> new PolymerPairCount(entry.getKey(), entry.getValue()))
            .collect(toList());
    }

    public PolymerTemplate applyRules(PolymerPairInsertionRules rules) {
        Map<PolymerPair, Long> counts = pairCounts
            .stream()
            .flatMap(count -> rules
                .calculateNewPairs(count.getPair())
                .stream()
                .map(count::withPair))
            .collect(groupingBy(PolymerPairCount::getPair, summingLong(PolymerPairCount::getCount)));
        return new PolymerTemplate(counts);
    }

    public static PolymerTemplate fromLine(String line) {
        Map<PolymerPair, Long> counts = IntStream
            .range(0, line.length())
            .mapToObj(index -> new PolymerPair(
                line.charAt(index),
                index == line.length() - 1 ? null : line.charAt(index + 1)))
            .collect(groupingBy(identity(), counting()));
        return new PolymerTemplate(counts);
    }

    public long calculateSize() {
        return pairCounts
            .stream()
            .mapToLong(PolymerPairCount::getCount)
            .sum();
    }

    public Map<Character, Long> calculateElementCounts() {
        return pairCounts
            .stream()
            .collect(groupingBy(pair -> pair.getPair().getLeftElement(), summingLong(PolymerPairCount::getCount)));
    }
}
