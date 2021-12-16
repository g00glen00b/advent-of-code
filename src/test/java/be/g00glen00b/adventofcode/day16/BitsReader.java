package be.g00glen00b.adventofcode.day16;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

@Slf4j
public class BitsReader {

    public static List<Packet> read(String data) {
        PositionType positionType = PositionType.HEADER;
        Queue<Character> dataList = mapToQueue(data);
        LinkedList<Packet> packets = new LinkedList<>();
        do {
            switch (positionType) {
                case HEADER:
                    positionType = readHeader(dataList, packets);
                    break;
                case BLOCK:
                    positionType = readBlock(dataList, packets);
                    break;
            }
        } while (positionType != PositionType.END);
        return packets;
    }

    private static PositionType readBlock(Queue<Character> dataList, LinkedList<Packet> packets) {
        String type = readElements(dataList, 1);
        String blockData = readElements(dataList, 4);
        Packet.Block block = new Packet.Block(type, blockData);
        packets.add(packets.removeLast().withBlock(block));
        return block.isLast() ? PositionType.END : PositionType.BLOCK;
    }

    private static PositionType readHeader(Queue<Character> dataList, LinkedList<Packet> packets) {
        String version = new BigInteger(readElements(dataList, 3), 2).toString();
        String type = new BigInteger(readElements(dataList, 3), 2).toString();
        Packet.Header header = new Packet.Header(version, type);
        packets.add(new Packet(header));
        return header.isLiteral() ? PositionType.BLOCK : PositionType.OPERATOR_TYPE;
    }

    private static String readElements(Queue<Character> data, int size) {
        return IntStream
            .range(0, size)
            .mapToObj(nr -> data.poll())
            .filter(Objects::nonNull)
            .map(Object::toString)
            .collect(joining());
    }

    private static Queue<Character> mapToQueue(String data) {
        return new BigInteger(data, 16)
            .toString(2)
            .chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    private enum PositionType {
        HEADER, BLOCK, OPERATOR_TYPE, OPERATOR_LENGTH_ZERO, OPERATOR_LENGTH_ONE, END
    }
}
