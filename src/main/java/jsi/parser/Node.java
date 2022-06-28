package jsi.parser;

/**
 * 节点
 *
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Node {

    private String type;

    private String operation;

    private String left;

    private String right;

    public String getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    static Node build(String type, String operation, String left, String right){
        Node node = new Node();
        node.type = type;
        node.operation = operation;
        node.left = left;
        node.right = right;
        return node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "type='" + type + '\'' +
                ", operation='" + operation + '\'' +
                ", left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
