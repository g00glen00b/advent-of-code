package be.g00glen00b.adventofcode.day1;

import lombok.Value;

@Value
public class DepthMeasurement {
    int lastDepth;
    int numberOfIncreases;

    public static DepthMeasurement initial() {
        return new DepthMeasurement(0, 0);
    }

    public DepthMeasurement withDepth(int newDepth) {
        if (lastDepth == 0) {
            return new DepthMeasurement(newDepth, 0);
        } else if (newDepth > lastDepth) {
            return new DepthMeasurement(newDepth, numberOfIncreases + 1);
        } else {
            return new DepthMeasurement(newDepth, numberOfIncreases);
        }
    }
}
