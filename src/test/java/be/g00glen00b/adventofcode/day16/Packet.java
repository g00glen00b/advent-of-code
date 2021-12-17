package be.g00glen00b.adventofcode.day16;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@With
@Value
@RequiredArgsConstructor
public class Packet {
    Header header;
    Operator operator;
    List<Block> blocks;
    List<Packet> subpackets;

    public int calculateVersionSum() {
        if (subpackets.isEmpty()) {
            return Integer.parseInt(header.getVersion());
        } else {
            return Integer.parseInt(header.getVersion()) + subpackets
                .stream()
                .mapToInt(Packet::calculateVersionSum)
                .sum();
        }
    }

    public long calculateResult() {
        if ("0".equals(header.getType())) return calculateSumOfSubpacketResults();
        else if ("1".equals(header.getType())) return calculateProductOfSubpacketResults();
        else if ("2".equals(header.getType())) return calculateMinimumOfSubpacketResults();
        else if ("3".equals(header.getType())) return calculateMaximumOfSubpacketResults();
        else if ("4".equals(header.getType())) return calculateBlockLiteral();
        else if ("5".equals(header.getType())) return calculateGreaterThanResult();
        else if ("6".equals(header.getType())) return calculateLessThanResult();
        else if ("7".equals(header.getType())) return calculateEqualToResult();
        else return 0;
    }

    private long calculateEqualToResult() {
        return subpackets.get(0).calculateResult() == subpackets.get(1).calculateResult() ? 1L : 0L;
    }

    private long calculateLessThanResult() {
        return subpackets.get(0).calculateResult() < subpackets.get(1).calculateResult() ? 1L : 0L;
    }

    private long calculateGreaterThanResult() {
        return subpackets.get(0).calculateResult() > subpackets.get(1).calculateResult() ? 1L : 0L;
    }

    private long calculateBlockLiteral() {
        String binaryRepresentation = blocks
            .stream()
            .map(Block::getData)
            .collect(joining());
        return new BigInteger(binaryRepresentation, 2).longValue();
    }

    private long calculateMaximumOfSubpacketResults() {
        return subpackets
            .stream()
            .mapToLong(Packet::calculateResult)
            .max()
            .orElse(0L);
    }

    private long calculateMinimumOfSubpacketResults() {
        return subpackets
            .stream()
            .mapToLong(Packet::calculateResult)
            .min()
            .orElse(0L);
    }

    private long calculateProductOfSubpacketResults() {
        return subpackets
            .stream()
            .mapToLong(Packet::calculateResult)
            .reduce(1L, (temporaryResult, subResult) -> temporaryResult * subResult);
    }

    private long calculateSumOfSubpacketResults() {
        return subpackets
            .stream()
            .mapToLong(Packet::calculateResult)
            .sum();
    }

    public Packet() {
        this(null, null, List.of(), List.of());
    }

    public Packet withBlock(Block block) {
        List<Block> copy = new ArrayList<>(this.blocks);
        copy.add(block);
        return withBlocks(List.copyOf(copy));
    }

    public Packet withSubpacket(Packet subpacket) {
        List<Packet> copy = new ArrayList<>(this.subpackets);
        copy.add(subpacket);
        return withSubpackets(List.copyOf(copy));
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

    @Value
    public static class Operator {
        String operatorType;
        int operatorLength;

        public boolean isTypeZero() {
            return "0".equals(operatorType);
        }
    }
}
