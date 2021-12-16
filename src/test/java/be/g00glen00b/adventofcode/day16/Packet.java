package be.g00glen00b.adventofcode.day16;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

@With
@Value
@RequiredArgsConstructor
public class Packet {
    Header header;
    List<Block> blocks;

    public Packet(Header header) {
        this(header, List.of());
    }

    public Packet withBlock(Block block) {
        List<Block> copy = new ArrayList<>(this.blocks);
        copy.add(block);
        return withBlocks(List.copyOf(copy));
    }

    @Value
    public static class Block {
        String type;
        String data;

        public boolean isLast() {
            return "0".equals(type);
        }
    }

    @Value
    public static class Header {
        String version;
        String type;

        public boolean isLiteral() {
            return "4".equals(type);
        }
    }

    public enum OperatorType {
        OPERATOR_TYPE_ZERO, OPERATOR_TYPE_ONE
    }
}
