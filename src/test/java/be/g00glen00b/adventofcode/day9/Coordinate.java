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

    public boolean isAdjacentTo(Coordinate otherCoordinate) {
        boolean adjacentX = Math.abs(x - otherCoordinate.getX()) == 1;
        boolean adjacentY = Math.abs(y - otherCoordinate.getY()) == 1;
        boolean sameX = x == otherCoordinate.getX();
        boolean sameY = y == otherCoordinate.getY();
        return (adjacentX && sameY) || (adjacentY && sameX);
    }

    public boolean isHigherThan(Coordinate otherCoordinate) {
        return height > otherCoordinate.getHeight();
    }
}
