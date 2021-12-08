package be.g00glen00b.adventofcode.day1;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Value
public class WindowMeasurement {
    int windowSize;
    List<Integer> lastDepths;
    int lastWindowDepth;
    int numberOfIncreases;

    public static WindowMeasurement initial(int windowSize) {
        return new WindowMeasurement(windowSize, List.of(), 0, 0);
    }

    public WindowMeasurement withDepth(int newDepth) {
        List<Integer> copyOfDepths = new ArrayList<>(lastDepths);
        if (lastDepths.size() < windowSize - 1) {
            copyOfDepths.add(newDepth);
            return new WindowMeasurement(windowSize, List.copyOf(copyOfDepths), lastWindowDepth, numberOfIncreases);
        } else if (lastDepths.size() == windowSize - 1) {
           copyOfDepths.add(newDepth);
            int windowDepth = copyOfDepths.stream().reduce(0, Integer::sum);
            return new WindowMeasurement(windowSize, List.copyOf(copyOfDepths), windowDepth, numberOfIncreases);
        } else {
            copyOfDepths.remove(0);
            copyOfDepths.add(newDepth);
            int windowDepth = copyOfDepths.stream().reduce(0, Integer::sum);
            if (windowDepth > lastWindowDepth) {
                return new WindowMeasurement(windowSize, List.copyOf(copyOfDepths), windowDepth, numberOfIncreases + 1);
            } else {
                return new WindowMeasurement(windowSize, List.copyOf(copyOfDepths), windowDepth, numberOfIncreases);
            }
        }
    }
}
