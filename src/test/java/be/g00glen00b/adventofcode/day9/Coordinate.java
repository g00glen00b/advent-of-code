package be.g00glen00b.adventofcode.day9;

import lombok.Value;

@Value
public class Coordinate {
    int x;
    int y;
    int height;

    public boolean isBasin() {
        return height != 9;
    }

    public boolean isHigherThan(Coordinate otherCoordinate) {
        return height > otherCoordinate.getHeight();
    }
}
