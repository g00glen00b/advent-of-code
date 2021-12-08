package be.g00glen00b.adventofcode.day2;

import lombok.Value;

@Value
public class SubmarinePosition {
    int horizontalPosition;
    int depth;

    public SubmarinePosition withCommand(Command command) {
        switch (command.getType()) {
            case UP: return new SubmarinePosition(horizontalPosition, depth - command.getDistance());
            case DOWN: return new SubmarinePosition(horizontalPosition, depth + command.getDistance());
            case FORWARD: return new SubmarinePosition(horizontalPosition + command.getDistance(), depth);
            default: return this;
        }
    }

    public static SubmarinePosition initial() {
        return new SubmarinePosition(0, 0);
    }
}
