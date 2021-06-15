package MyHashTable;

import java.util.Arrays;

/**
 * 1.index是怎么产生的？
 */
public class MyHashTable {
    //1.定义Node类型的数组
    private  Node[] array = new Node[11];
    //2.维护哈希表中的元素个数
    private int size;

    public boolean insert(Integer key){
        //1.把对象转为int类型，调用hashCode方法
        int hashValue= key.hashCode();
        //2.把hashValue转成合法下标
        int index = hashValue % array.length;
        //3.遍历index位置的链表，确定key不在元素中
        Node cur = array[index];
        while(cur != null){
            if(key.equals(cur.key)){
                return false;
            }
            cur = cur.next;
        }
        //4.把key装入节点中，插入到相应链表里
        Node node = new Node(key);//创造一个新的节点
        node.next = array[index];
        array[index] = node;
        //5.维护元素个数
        size ++;
        //6.通过维护负载因子，来维护较低的冲突率
        if(size/array.length * 100 >= 75){
            ensureCapacity();
        }
        return true;
    }

    public boolean remove(Integer key){
        int hashValue = key.hashCode();
        int index = hashValue % array.length;

        Node prev = null;
        Node cur = array[index];
        while(cur != null){
            if(key.equals(cur.key)){
                if(prev != null){
                    prev.next = cur.next;
                }else{
                    //cur是这条链表的头结点
                    array[index] = cur.next;
                }

                size--;
                return true;
            }
            //更新循环变量
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public boolean contains(Integer key){
        int hashValue = key.hashCode();
        int index = hashValue % array.length;

        Node cur = array[index];
        while(cur != null){
            if(key.equals(cur.key)){
                return  true;
            }
            cur = cur.next;
        }
        return false;
    }


    private void ensureCapacity() {
        Node[] newArray = new Node[array.length * 2];
        for(int i = 0;i < array.length;i++){
            Node cur = array[i];
            while(cur != null){
                //复制结点
                Integer value= cur.key;
                int hashValue =value.hashCode();
                int index = hashValue % newArray.length;

                Node node = new Node(value);
                node.next = newArray[index];
                newArray[index] = node;

                cur = cur.next;
            }
        }
        array = newArray;
    }

}
