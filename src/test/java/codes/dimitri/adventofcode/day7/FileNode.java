package codes.dimitri.adventofcode.day7;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FileNode implements Node {
    private final DirectoryNode parent;
    @Getter
    private final String name;
    private final int size;

    @Override
    public NodeType getType() {
        return NodeType.FILE;
    }

    @Override
    public Optional<DirectoryNode> getParent() {
        return Optional.of(parent);
    }

    @Override
    public Collection<Node> getChildren() {
        return List.of();
    }

    @Override
    public int calculateSize() {
        return size;
    }
}
