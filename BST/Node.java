package BST;

public class Node {
    public Integer key;
    Node left;
    Node right;

    public Node(Integer key) {
        this.key = key;
    }

    @Override
    public String toString() {
        //重写toString方法，只需要打印key值就行
        return "Node{" +
                "key=" + key +
                '}';
    }
}
