package MyHashTable;

public class TestCase {
    public static void main(String[] args) {
        int[] elements = {103,29,38,4,7,55,907,32};
        MyHashTable hashTable = new MyHashTable();
        for (int e :elements){
            hashTable.insert(e);
        }
        System.out.println("hello");
    }
}
