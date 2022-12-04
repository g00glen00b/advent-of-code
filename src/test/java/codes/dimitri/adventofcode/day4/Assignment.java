package codes.dimitri.adventofcode.day4;

public record Assignment(int startSection, int endSectionInclusive) {
    public boolean isFullyContaining(Assignment otherAssignment) {
        return startSection <= otherAssignment.startSection() && endSectionInclusive >= otherAssignment.endSectionInclusive();
    }

    public boolean isOverlapping(Assignment otherAssignment) {
        return startSection <= otherAssignment.endSectionInclusive() && otherAssignment.startSection() <= endSectionInclusive;
    }

    public static Assignment ofString(String assignment) {
        String[] sections = assignment.split("-");
        int startSection = Integer.parseInt(sections[0]);
        int endSection = Integer.parseInt(sections[1]);
        return new Assignment(startSection, endSection);
    }
}
