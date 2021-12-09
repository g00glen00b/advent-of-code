package be.g00glen00b.adventofcode.day8;

import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Value
public class Display {
    private static final int POSITION_DIGIT_ONE = 0;
    private static final int POSITION_DIGIT_SEVEN = 1;
    private static final int POSITION_DIGIT_FOUR = 2;
    private static final int START_POSITION_DIGIT_LENGTH_FIVE = 3;
    private static final int END_POSITION_DIGIT_LENGTH_FIVE = 6;
    private static final int POSITION_DIGIT_EIGHT = 9;
    char top;
    char topLeft;
    char topRight;
    char middle;
    char bottomLeft;
    char bottomRight;
    char bottom;

    public int decode(String output) {
        String result = Arrays
            .stream(output.split(" "))
            .map(this::decodeDigit)
            .map(Object::toString)
            .collect(joining());
        return Integer.parseInt(result);
    }

    public int decodeDigit(String segments) {
        List<Character> segmentList = segmentList(segments);
        if (segmentList.size() == 2) return 1;
        else if (segmentList.size() == 3) return 7;
        else if (segmentList.size() == 4) return 4;
        else if (segmentList.size() == 7) return 8;
        else if (segmentList.contains(bottomLeft)) {
            if (!segmentList.contains(middle)) return 0;
            else if (!segmentList.contains(topRight)) return 6;
            else return 2;
        } else if (segmentList.size() == 6) return 9;
        else if (segmentList.contains(topLeft)) return 5;
        else return 3;
    }

    public static Display fromInput(String input) {
        List<List<Character>> list = listOfSegmentLists(input);
        List<Character> segmentsOne = list.get(POSITION_DIGIT_ONE);
        List<Character> segmentsSeven = list.get(POSITION_DIGIT_SEVEN);
        List<Character> segmentsFour = list.get(POSITION_DIGIT_FOUR);
        List<Character> segmentsEight = list.get(POSITION_DIGIT_EIGHT);
        Character topSegment = calculateTopSegment(segmentsOne, segmentsSeven);
        List<List<Character>> digitsLengthFive = list.subList(START_POSITION_DIGIT_LENGTH_FIVE, END_POSITION_DIGIT_LENGTH_FIVE);
        List<Character> segmentsThree = calculateSegentsThree(digitsLengthFive, segmentsOne);
        Character middleSegment = calculateMiddleSegment(segmentsThree, segmentsFour, segmentsOne);
        Character topLeftSegment = calculateTopleftSegment(segmentsFour, segmentsOne, middleSegment);
        Character bottomSegment = calculateBottomSegment(segmentsThree, segmentsOne, topSegment, middleSegment);
        List<Character> segmentsFive = calculateSegmentsFive(digitsLengthFive, topLeftSegment);
        Character bottomRightSegment = calculateBottomRightSegment(segmentsFive, segmentsOne);
        Character topRightSegment = calculateTopRightSegment(segmentsOne, bottomRightSegment);
        Character bottomLeftSegment = calculateBottomLeftSegment(segmentsEight, segmentsFive, segmentsOne);
        return new Display(topSegment, topLeftSegment, topRightSegment, middleSegment, bottomLeftSegment, bottomRightSegment, bottomSegment);
    }

    private static Character calculateBottomLeftSegment(List<Character> segmentsEight, List<Character> segmentsFive, List<Character> segmentsOne) {
        List<Character> copySegmentsEight = new ArrayList<>(segmentsEight);
        copySegmentsEight.removeAll(segmentsFive);
        copySegmentsEight.removeAll(segmentsOne);
        return copySegmentsEight.iterator().next();
    }

    private static Character calculateBottomRightSegment(List<Character> segmentsFive, List<Character> segmentsOne) {
        return segmentsOne
            .stream()
            .filter(segmentsFive::contains)
            .findAny()
            .orElseThrow();
    }

    private static Character calculateTopRightSegment(List<Character> segmentsOne, Character bottomRightSegment) {
        List<Character> copyOfSegmentsOne = new ArrayList<>(segmentsOne);
        copyOfSegmentsOne.remove(bottomRightSegment);
        return copyOfSegmentsOne.iterator().next();
    }

    private static Character calculateMiddleSegment(List<Character> segmentsThree, List<Character> segmentsFour, List<Character> segmentsOne) {
        List<Character> copyOfSegmentsThree = new ArrayList<>(segmentsThree);
        copyOfSegmentsThree.removeAll(segmentsOne);
        return copyOfSegmentsThree
            .stream()
            .filter(segmentsFour::contains)
            .findAny()
            .orElseThrow();
    }

    private static Character calculateTopleftSegment(List<Character> segmentsFour, List<Character> segmentsOne, Character middleSegment) {
        List<Character> copyOfSegmentsFour = new ArrayList<>(segmentsFour);
        copyOfSegmentsFour.removeAll(segmentsOne);
        copyOfSegmentsFour.remove(middleSegment);
        return copyOfSegmentsFour.iterator().next();
    }

    private static Character calculateBottomSegment(List<Character> segmentsThree, List<Character> segmentsOne, Character topSegment, Character middleSegment) {
        List<Character> copyOfSegmentsThree = new ArrayList<>(segmentsThree);
        copyOfSegmentsThree.removeAll(segmentsOne);
        copyOfSegmentsThree.remove(topSegment);
        copyOfSegmentsThree.remove(middleSegment);
        return copyOfSegmentsThree.iterator().next();
    }

    private static List<Character> calculateSegmentsFive(List<List<Character>> digitsLengthFive, Character topLeftSegment) {
        return digitsLengthFive
            .stream()
            .filter(digit -> digit.contains(topLeftSegment))
            .findAny()
            .orElseThrow();
    }

    private static List<Character> calculateSegentsThree(List<List<Character>> digitsLengthFive, List<Character> segmentsOne) {
        return digitsLengthFive
            .stream()
            .filter(digit -> containsAll(digit, segmentsOne))
            .findAny()
            .orElseThrow();
    }

    private static List<List<Character>> listOfSegmentLists(String input) {
        return Arrays
            .stream(input.split(" "))
            .sorted(comparing(String::length))
            .map(Display::segmentList)
            .collect(toList());
    }

    private static List<Character> segmentList(String value) {
        return value
            .chars()
            .mapToObj(segment -> (char) segment)
            .collect(toList());
    }

    private static Character calculateTopSegment(List<Character> segmentsOne, List<Character> segmentsSeven) {
        List<Character> copyOfSegmentsSeven = new ArrayList<>(segmentsSeven);
        copyOfSegmentsSeven.removeAll(segmentsOne);
        return copyOfSegmentsSeven.iterator().next();
    }

    private static boolean containsAll(List<Character> segments, List<Character> segmentsOne) {
        return segments.containsAll(segmentsOne);
    }
}
