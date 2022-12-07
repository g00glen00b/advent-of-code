package codes.dimitri.adventofcode.day7;

import java.util.Collection;
import java.util.Optional;

public interface Node {
    NodeType getType();
    String getName();
    Optional<DirectoryNode> getParent();
    Collection<Node> getChildren();
    int calculateSize();
}
