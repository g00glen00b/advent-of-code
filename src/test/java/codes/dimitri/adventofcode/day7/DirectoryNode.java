package codes.dimitri.adventofcode.day7;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class DirectoryNode implements Node {
    private final DirectoryNode parent;
    @Getter
    private final String name;
    private final List<Node> children;

    public static DirectoryNode root() {
        return new DirectoryNode(null, "/", new ArrayList<>());
    }

    @Override
    public NodeType getType() {
        return NodeType.DIRECTORY;
    }

    @Override
    public Optional<DirectoryNode> getParent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public Collection<Node> getChildren() {
        return List.copyOf(children);
    }

    public DirectoryNode findDirectory(String name) {
        return streamChildDirectories()
            .filter(node -> node.getName().equals(name))
            .findAny()
            .orElseThrow(() -> new RuntimeException("Directory not found: " + name));
    }

    public List<DirectoryNode> flattenDirectories() {
        Stream<DirectoryNode> directories = streamChildDirectories()
            .map(DirectoryNode::flattenDirectories)
            .flatMap(Collection::stream);
        return Stream
            .concat(Stream.of(this), directories)
            .toList();
    }

    private Stream<DirectoryNode> streamChildDirectories() {
        return children
            .stream()
            .filter(node -> node instanceof DirectoryNode)
            .map(node -> (DirectoryNode) node);
    }

    @Override
    public int calculateSize() {
        return children
            .stream()
            .mapToInt(Node::calculateSize)
            .sum();
    }

    public FileNode addFileNode(String name, int size) {
        FileNode child = new FileNode(this, name, size);
        children.add(child);
        return child;
    }

    public DirectoryNode addDirectoryNode(String name) {
        DirectoryNode child = new DirectoryNode(this, name, new ArrayList<>());
        children.add(child);
        return child;
    }
}
