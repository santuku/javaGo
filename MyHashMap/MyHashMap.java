package MyHashMap;

public class MyHashMap {
    private Node[] array = new Node[11];
    private int size;

    public Integer get(Integer key){
        int hashValue = key.hashCode();
        int index = hashValue % array.length;

        Node cur = array[index];
        while(cur != null){
            if(key.equals(cur.key)){
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    public Integer put(Integer key,Integer value){
        int hashValue = key.hashCode();
        int index = hashValue % array.length;

        Node cur = array[index];
        while(cur != null){
            if(key.equals(cur.key)){
                Integer oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
            cur = cur.next;
        }

        Node node = new Node(key,value);
        node.next = array[index];
        array[index] = node;

        size ++;
        return null;
    }
}
