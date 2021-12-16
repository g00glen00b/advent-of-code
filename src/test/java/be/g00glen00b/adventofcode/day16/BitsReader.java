package be.g00glen00b.adventofcode.day16;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

@Slf4j
public class BitsReader {

    public static Packet readPacket(String data) {
        Queue<Character> dataList = mapToQueue(convertHexadecimalToPaddedBinary(data));
        return readPacket(dataList);
    }

    private static Packet readPacket(Queue<Character> dataList) {
        ReadResult result = new ReadResult(new Packet(), ReadResult.PositionType.HEADER);
        do {
            switch (result.getPositionType()) {
                case HEADER:
                    result = readHeader(dataList, result.getPacket());
                    break;
                case BLOCK:
                    result = readBlock(dataList, result.getPacket());
                    break;
                case OPERATOR:
                    result = readOperator(dataList, result.getPacket());
                    break;
                case SUBPACKET:
                    result = readSubpackets(dataList, result.getPacket());
                    break;
            }
        } while (!result.isEnd());
        return result.getPacket();
    }

    private static ReadResult readBlock(Queue<Character> dataList, Packet packet) {
        String type = readElements(dataList, 1);
        String blockData = readElements(dataList, 4);
        Packet.Block block = new Packet.Block(type, blockData);
        ReadResult.PositionType positionType = block.isLast() ? ReadResult.PositionType.END : ReadResult.PositionType.BLOCK;
        return new ReadResult(packet.withBlock(block), positionType);
    }

    private static ReadResult readHeader(Queue<Character> dataList, Packet packet) {
        String version = new BigInteger(readElements(dataList, 3), 2).toString();
        String type = new BigInteger(readElements(dataList, 3), 2).toString();
        Packet.Header header = new Packet.Header(version, type);
        ReadResult.PositionType positionType = header.isLiteral() ? ReadResult.PositionType.BLOCK : ReadResult.PositionType.OPERATOR;
        return new ReadResult(packet.withHeader(header), positionType);
    }

    private static ReadResult readOperator(Queue<Character> dataList, Packet packet) {
        String operatorType = readElements(dataList, 1);
        String operatorLength = readElements(dataList, "0".equals(operatorType) ? 15 : 11);
        int length = new BigInteger(operatorLength, 2).intValue();
        Packet.Operator operator = new Packet.Operator(operatorType, length);
        return new ReadResult(packet.withOperator(operator), ReadResult.PositionType.SUBPACKET);
    }

    private static ReadResult readSubpackets(Queue<Character> dataList, Packet packet) {
        Packet result = packet;
        if (packet.getOperator().isTypeZero()) {
            String subdata = readElements(dataList, packet.getOperator().getOperatorLength());
            Queue<Character> subdataList = mapToQueue(subdata);
            do {
                Packet subpacket = readPacket(subdataList);
                result = result.withSubpacket(subpacket);
            } while (!isEmptyQueue(subdataList));
        } else {
            for (int count = 1; count <= packet.getOperator().getOperatorLength(); count++) {
                Packet subpacket = readPacket(dataList);
                result = result.withSubpacket(subpacket);
            }
        }
        return new ReadResult(result, ReadResult.PositionType.END);
    }

    private static String readElements(Queue<Character> data, int size) {
        return IntStream
            .range(0, size)
            .mapToObj(nr -> data.poll())
            .filter(Objects::nonNull)
            .map(Object::toString)
            .collect(joining());
    }

    private static Queue<Character> mapToQueue(String binaryData) {
        return binaryData
            .chars()
            .mapToObj(c -> (char) c)
            .collect(toCollection(LinkedList::new));
    }

    private static String convertHexadecimalToPaddedBinary(String hexadecimalData) {
        String binaryData = new BigInteger(hexadecimalData, 16).toString(2);
        return String.format("%" + (hexadecimalData.length() * 4) + "s", binaryData).replaceAll(" ", "0");
    }

    private static boolean isEmptyQueue(Queue<Character> datalist) {
        return datalist.isEmpty() || datalist.stream().allMatch(c -> c.equals('0'));
    }
}
