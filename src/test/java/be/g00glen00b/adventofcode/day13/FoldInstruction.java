package be.g00glen00b.adventofcode.day13;

import lombok.Value;

import java.util.Locale;

import static java.lang.Integer.parseInt;

@Value
public class FoldInstruction {
    FoldType type;
    int value;

    public static FoldInstruction fromLine(String line) {
        String foldTypeSymbol = line.substring(11, 12);
        String valueString = line.substring(13);
        return new FoldInstruction(FoldType.valueOf(foldTypeSymbol.toUpperCase(Locale.ROOT)), parseInt(valueString));
    }

    public boolean isXType() {
        return FoldType.X.equals(type);
    }

    public boolean isYType() {
        return FoldType.Y.equals(type);
    }

    private enum FoldType {
        X, Y
    }
}
