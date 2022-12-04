package codes.dimitri.adventofcode.day4;

public record AssignmentPair(Assignment assignment1, Assignment assignment2) {
    public static AssignmentPair ofLine(String line) {
        String[] assignments = line.split(",");
        Assignment assignment1 = Assignment.ofString(assignments[0]);
        Assignment assignment2 = Assignment.ofString(assignments[1]);
        return new AssignmentPair(assignment1, assignment2);
    }

    public boolean isFullyContained() {
        return assignment1.isFullyContaining(assignment2) || assignment2.isFullyContaining(assignment1);
    }

    public boolean isOverlapping() {
        return assignment1.isOverlapping(assignment2);
    }
}
