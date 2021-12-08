package be.g00glen00b.adventofcode.day4;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

@Value
public class LastWinnerBingoBoards {
    List<BingoBoard> boards;
    BingoBoard lastWinner;
    int lastValue;

    public static LastWinnerBingoBoards fromLines(List<String> lines) {
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
        return new LastWinnerBingoBoards(boards, null, -1);
    }

    public Optional<BingoBoard> findLoser() {
        return boards
            .stream()
            .filter(not(BingoBoard::isComplete))
            .findAny();
    }

    public LastWinnerBingoBoards mark(int number) {
        if (lastWinner != null) {
            return this;
        } else {
            List<BingoBoard> newBoards = boards.stream().map(board -> board.mark(number)).collect(toList());
            LastWinnerBingoBoards newResult = new LastWinnerBingoBoards(newBoards, null, number);
            if (newResult.findLoser().isEmpty()) {
                return new LastWinnerBingoBoards(newBoards, findLoser()
                    .map(loser -> loser.mark(number))
                    .orElse(null), number);
            } else {
                return newResult;
            }
        }
    }
}
