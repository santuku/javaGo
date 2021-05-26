package linkedlist;

import java.util.Iterator;

public class LinkedListIterator implements Iterator {

    private LinkedList list;//需要迭代的双链表
    private Node current;//双链表中的单个元素：节点

    public LinkedListIterator(LinkedList list, Node current) {
        this.list = list;
        this.current = current;
    }

    @Override
    public boolean hasNext() {
       return current != null;
    }

    @Override
    public Object next() {
        Integer e = current.element;
        current = current.next;
        return e;
    }
}
