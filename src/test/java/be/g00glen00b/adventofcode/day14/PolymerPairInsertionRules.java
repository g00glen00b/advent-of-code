package be.g00glen00b.adventofcode.day14;

import lombok.Value;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Value
public class PolymerPairInsertionRules {
    Map<PolymerPair, List<PolymerPair>> rules;

    public List<PolymerPair> calculateNewPairs(PolymerPair pair) {
        return rules.getOrDefault(pair, List.of(pair));
    }

    public static PolymerPairInsertionRules fromLines(List<String> lines) {
        Map<PolymerPair, List<PolymerPair>> rules = lines
            .stream()
            .collect(toMap(
                line -> new PolymerPair(line.charAt(0), line.charAt(1)),
                line -> List.of(
                    new PolymerPair(line.charAt(0), line.charAt(6)),
                    new PolymerPair(line.charAt(6), line.charAt(1))
                )
            ));
        return new PolymerPairInsertionRules(rules);
    }
}
