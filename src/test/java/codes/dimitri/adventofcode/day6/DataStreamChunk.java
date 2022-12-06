package codes.dimitri.adventofcode.day6;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toUnmodifiableSet;

public record DataStreamChunk(String chunk, int position) {
    public boolean isStartSignal() {
        Set<Character> uniqueCharacters = chunk
            .chars()
            .mapToObj(character -> (char) character)
            .collect(toUnmodifiableSet());
        return uniqueCharacters.size() == chunk.length();
    }

    public static List<DataStreamChunk> fromDataStreamBuffer(String buffer, int chunkLength) {
        return IntStream
            .range(chunkLength, buffer.length() + 1)
            .mapToObj(position -> new DataStreamChunk(buffer.substring(position - chunkLength, position), position))
            .toList();
    }
}
