package be.g00glen00b.adventofcode.day2;

import lombok.Value;

@Value
public class SubmarineAimPosition {
    int horizontalPosition;
    int depth;
    int aim;

    public SubmarineAimPosition withCommand(Command command) {
        switch (command.getType()) {
            case UP: return new SubmarineAimPosition(horizontalPosition, depth, aim - command.getDistance());
            case DOWN: return new SubmarineAimPosition(horizontalPosition, depth, aim + command.getDistance());
            case FORWARD: return new SubmarineAimPosition(horizontalPosition + command.getDistance(), depth + (command.getDistance() * aim), aim);
            default: return this;
        }
    }

    public static SubmarineAimPosition initial() {
        return new SubmarineAimPosition(0, 0, 0);
    }
}
