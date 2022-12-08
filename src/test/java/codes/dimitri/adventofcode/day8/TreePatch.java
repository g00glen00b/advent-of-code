package codes.dimitri.adventofcode.day8;

public record TreePatch(int height) {
    public boolean isSmallerThan(TreePatch otherPatch) {
        return height < otherPatch.height();
    }
}
