package be.g00glen00b.adventofcode.day16;

import lombok.Value;

@Value
public class ReadResult {
    Packet packet;
    PositionType positionType;

    public boolean isEnd() {
        return PositionType.END.equals(positionType);
    }

    public enum PositionType {
        HEADER, BLOCK, OPERATOR, SUBPACKET, END
    }
}
