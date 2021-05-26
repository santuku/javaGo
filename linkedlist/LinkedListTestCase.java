package linkedlist;

public class LinkedListTestCase  {
    public static void main(String[] args) {
        List list = new LinkedList();
        list.add(0,99);
        list.add(11);
        list.add(22);
        list.add(33);

        System.out.println(list);//[99,11,22,33]
        list.add(0,1000);
        System.out.println(list);
        list.add(3,28);
        System.out.println(list);
        list.add(1,90);
        System.out.println(list);
    }
}
