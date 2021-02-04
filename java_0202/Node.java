package java_0202;

public class Node {
    public int item;
    public Node next; //= null;//为什么不可以不初始化？
    public Node(int item){
        //构造器的this代表要进行初始化的对象
        this.item = item;
    }

    public String toString(){
        return "[" + item + "]";
    }
}