package be.g00glen00b.adventofcode.day4;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Value
public class BingoBoards {
    List<BingoBoard> boards;
    int lastValue;

    public static BingoBoards fromLines(List<String> lines) {
        List<String> currentBoard = new ArrayList<>();
        List<BingoBoard> boards = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) {
                currentBoard.add(line);
            } else if (!currentBoard.isEmpty()) {
                boards.add(BingoBoard.fromLines(currentBoard));
                currentBoard = new ArrayList<>();
            }
        }
        if (!currentBoard.isEmpty()) {
            boards.add(BingoBoard.fromLines(currentBoard));
        }
        return new BingoBoards(boards, -1);
    }

    public Optional<BingoBoard> findWinner() {
        return boards
            .stream()
            .filter(BingoBoard::isComplete)
            .findAny();
    }

    public BingoBoards mark(int number) {
        if (findWinner().isPresent()) {
            return this;
        } else {
            List<BingoBoard> newBoards = boards.stream().map(board -> board.mark(number)).collect(toList());
            return new BingoBoards(newBoards, number);
        }
    }
}
