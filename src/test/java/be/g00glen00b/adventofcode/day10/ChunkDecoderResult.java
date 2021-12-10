package be.g00glen00b.adventofcode.day10;

import lombok.Value;

import java.util.ArrayDeque;
import java.util.Deque;

import static be.g00glen00b.adventofcode.TestUtils.unsupported;

@Value
public class ChunkDecoderResult {
    Deque<Chunk> chunksInProgress;
    Character corruptCharacter;

    public int calculateErrorScore() {
        if (corruptCharacter.equals(')')) return 3;
        else if (corruptCharacter.equals(']')) return 57;
        else if (corruptCharacter.equals('}')) return 1197;
        else if (corruptCharacter.equals('>')) return 25137;
        else return 0;
    }

    public long calculateCompletionScore() {
        return chunksInProgress
            .stream()
            .mapToLong(Chunk::getCompletionScore)
            .reduce(0L, (result, score) -> result * 5L + score);
    }

    public boolean isCorrupt() {
        return corruptCharacter != null;
    }

    public boolean isIncomplete() {
        return !chunksInProgress.isEmpty();
    }

    public static ChunkDecoderResult empty() {
        return new ChunkDecoderResult(new ArrayDeque<>(), null);
    }

    public static ChunkDecoderResult fromLine(String line) {
        return line
            .chars()
            .mapToObj(segment -> (char) segment)
            .reduce(ChunkDecoderResult.empty(), ChunkDecoderResult::withDelimiter, unsupported());
    }

    public ChunkDecoderResult withDelimiter(char delimiter) {
        Deque<Chunk> result = new ArrayDeque<>(chunksInProgress);
        if (corruptCharacter != null) {
            return this;
        } else if (!result.isEmpty() && result.getFirst().isClosingDelimiter(delimiter)) {
            result.removeFirst();
            return new ChunkDecoderResult(result, null);
        } else {
            return Chunk
                .fromOpeningDelimiter(delimiter)
                .stream()
                .peek(result::addFirst)
                .map(chunk -> new ChunkDecoderResult(result, null))
                .findAny()
                .orElseGet(() -> new ChunkDecoderResult(result, delimiter));
        }

    }
}
