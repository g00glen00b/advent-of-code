package be.g00glen00b.adventofcode.day16;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static be.g00glen00b.adventofcode.TestUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

public class Day16Test {
    @Test
    void partOne_literal() {
        Packet packet = BitsReader.readPacket("D2FE28");
        assertThat(packet).isEqualToComparingFieldByField(new Packet(
           new Packet.Header("6", "4"),
           null,
            List.of(
                new Packet.Block("1", "0111"),
                new Packet.Block("1", "1110"),
                new Packet.Block("0", "0101")),
            List.of()
        ));
        assertThat(packet.calculateResult()).isEqualTo(2021);
    }

    @Test
    void partOne_operatorZero() {
        Packet packet = BitsReader.readPacket("38006F45291200");
        assertThat(packet).isEqualToComparingFieldByField(new Packet(
           new Packet.Header("1", "6"),
           new Packet.Operator("0", 27),
           List.of(),
           List.of(
               new Packet(
                   new Packet.Header("6", "4"),
                   null,
                   List.of(new Packet.Block("0", "1010")),
                   List.of()
               ),
               new Packet(
                   new Packet.Header("2", "4"),
                   null,
                   List.of(
                       new Packet.Block("1", "0001"),
                       new Packet.Block("0", "0100")),
                   List.of()
               )
           )
        ));
    }

    @Test
    void partOne_operatorOne() {
        Packet packet = BitsReader.readPacket("EE00D40C823060");
        assertThat(packet).isEqualToComparingFieldByField(new Packet(
            new Packet.Header("7", "3"),
            new Packet.Operator("1", 3),
            List.of(),
            List.of(
                new Packet(
                    new Packet.Header("2", "4"),
                    null,
                    List.of(new Packet.Block("0", "0001")),
                    List.of()
                ),
                new Packet(
                    new Packet.Header("4", "4"),
                    null,
                    List.of(new Packet.Block("0", "0010")),
                    List.of()
                ),
                new Packet(
                    new Packet.Header("1", "4"),
                    null,
                    List.of(new Packet.Block("0", "0011")),
                    List.of()
                )
            )
        ));
    }

    @ParameterizedTest
    @CsvSource({
        "8A004A801A8002F478,16",
        "620080001611562C8802118E34,12",
        "C0015000016115A2E0802F182340,23",
        "A0016C880162017C3686B18A3D4780,31"
    })
    void partOne_examples(String data, int expectedOutput) {
        Packet packet = BitsReader.readPacket(data);
        assertThat(packet.calculateVersionSum()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day16_full.txt,955"
    })
    void partOne(String fileLocation, int expectedOutput) throws URISyntaxException, IOException {
        String data = readLines(fileLocation).get(0);
        Packet packet = BitsReader.readPacket(data);
        assertThat(packet.calculateVersionSum()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "C200B40A82,3",
        "04005AC33890,54",
        "880086C3E88112,7",
        "CE00C43D881120,9",
        "D8005AC2A8F0,1",
        "F600BC2D8F,0",
        "9C005AC2F8F0,0",
        "9C0141080250320F1802104A08,1"
    })
    void partTwo_example(String hexadecimalData, int expectedOutput) {
        Packet packet = BitsReader.readPacket(hexadecimalData);
        assertThat(packet.calculateResult()).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
        "/day16_full.txt,158135423448"
    })
    void partTwo(String fileLocation, long expectedOutput) throws URISyntaxException, IOException {
        String data = readLines(fileLocation).get(0);
        Packet packet = BitsReader.readPacket(data);
        assertThat(packet.calculateResult()).isEqualTo(expectedOutput);
    }
}
