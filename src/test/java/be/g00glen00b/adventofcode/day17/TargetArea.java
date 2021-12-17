package be.g00glen00b.adventofcode.day17;

import lombok.Value;

import static java.lang.Math.min;

@Value
public class TargetArea {
    int x;
    int y;
    int width;
    int height;

    public boolean isWithinArea(Probe probe) {
        return probe.getX() >= x && probe.getX() <= x + width && probe.getY() >= y && probe.getY() <= y + height;
    }

    public boolean isBelowArea(Probe probe) {
        return probe.getY() < min(y, y + height);
    }
}
