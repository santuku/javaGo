package linkedlist;

import java.util.Spliterator;

public class LinkedList implements List {
    public Node head;//指向第一个结点
    public Node last;//指向最后一个结点
    public int size;

    @Override
    public boolean add(Integer e) {//O(1)
        Node newNode = new Node (e);
        if(size == 0){
            //这里判断链表为空的情况，也可以是head = last = null;
            this.head = this.last = newNode;
        }else{
            this.last.next = newNode;
            newNode.prev = this.last;
            this.last = newNode;
        }
        this.size++;
        return true;
    }

    @Override
    public void add(int index,Integer e){
        Node newNode = new Node(e);

        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("下标越界" +index);
        }else if(index == size){//尾插，这个判断放在前面可以排除没有结点的情况
            add(e);
        }else if(index == 0){//头插
            newNode.next =this.head;
            this.head.prev = newNode;
            this.head = newNode;

            size++;
        }else{
            //其他情况
            //找到index -1所在的位置结点
            Node prev;
            if(index - 1 < size / 2){
                prev = head;
                for (int i = 0; i < index - 1 ; i++) {
                    prev = prev.next;
                }
            }else {
                prev = last;
                for (int i = 0; i < size - index; i++) {
                    prev = prev.prev;
                }
            }
            Node next = prev.next;
            //要改变四个引用
            newNode.prev = prev;
            newNode.next = prev.next;
            prev.next = newNode;
            next.prev = newNode;

            size++;
        }
    }

    @Override
    public boolean remove(Integer e) {
        Node prev = null;
        for(Node cur = head; cur != null;prev = cur,cur = cur.next){
            if(cur.element.equals(e)){
                if(prev == null){

                }else{
                    prev.next.next.prev = cur;
                    prev.next = prev.next.next;
                    size --;
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界" + index);
        }
        //这一步后size一定是大于0的
        Integer value;
        if (index == 0) {//头删
            value = head.element;
            this.head = head.next;
            this.head.prev = null;
            size--;
            if (size == 0) {
                last = null;
            }
        } else if (index == size - 1) {//尾删
                value = last.element;

                this.last = this.last.prev;
                this.last.next = null;
                size--;
                if (size == 0) {
                    head = null;
                }
        } else {//其他情况
                Node prev;
                if (index - 1 < size / 2) {
                    prev = head;
                    for (int i = 0; i < index - 1; i++) {
                        prev = prev.next;
                    }
                } else {
                    prev = last;
                    for (int i = 0; i < size - index; i++) {
                        prev = prev.prev;
                    }
                }
                Node toRemove = prev.next;//定义被删除的结点
                //改变两个引用
                value = toRemove.element;
                prev.next = toRemove.next;
                toRemove.next.prev = prev;
                size--;
        }
        return  value;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界" + index);
        }
        Node cur = head;
        for (int i = 0; i < index ; i++) {//这里的循环条件没有=号
            cur = cur.next;
        }
        return cur.element;
    }

    @Override
    public Integer set(int index, Integer e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("下标越界" + index);
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        Integer v = cur.element;
        cur.element = e;
        return v;
    }

    @Override
    public void clear() {
        size = 0;
        head = last = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Integer e) {
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(Integer e) {
       int i = 0;
       for (Node cur = head; cur != null;cur = cur.next,i++){
           if(cur.element.equals(e)){
               return i;
           }
       }
       return -1;
    }

    @Override
    public int lastIndexOf(Integer e) {
        int i = size -1;
        for(Node cur =last;cur != null;cur = cur.prev,i--){
            if(cur.element.equals(e)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder("[");
        for(Node cur = head; cur != null;cur = cur.next){
            sb.append(cur.element);
            if(cur != last){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
