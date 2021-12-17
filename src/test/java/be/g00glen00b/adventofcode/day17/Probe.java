package be.g00glen00b.adventofcode.day17;

import lombok.Value;

import static java.lang.Math.max;

@Value
public class Probe {
    int x;
    int y;
    int highestY;
    int horizontalSpeed;
    int verticalSpeed;

    public Probe calculateNext() {
        int newY = y + verticalSpeed;
        int newHighestY = max(newY, highestY);
        if (horizontalSpeed > 0) {
            return new Probe(x + horizontalSpeed, newY, newHighestY, horizontalSpeed - 1, verticalSpeed - 1);
        } else if (horizontalSpeed < 0) {
            return new Probe(x + horizontalSpeed, newY, newHighestY, horizontalSpeed + 1, verticalSpeed - 1);
        } else {
            return new Probe(x + horizontalSpeed, newY, newHighestY, 0, verticalSpeed - 1);
        }
    }

    public boolean isFalling() {
        return verticalSpeed < 0;
    }
}
