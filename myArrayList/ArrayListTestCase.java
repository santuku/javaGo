package myArrayList;

public class ArrayListTestCase {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("list = " + list);   // [1, 2, 3, 4]
        System.out.println("size = " + list.size());    // 4

        list.add(0, 100);
        System.out.println("list = " + list);   // [100, 1, 2, 3, 4]
        System.out.println("size = " + list.size());    // 5

        list.add(5, 200);
        System.out.println("list = " + list);   // [100, 1, 2, 3, 4, 200]
        System.out.println("size = " + list.size());    // 6

        try {
            list.add(-1, 300);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            list.add(7, 300);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        list.remove(3); // 按照下标删除
        System.out.println("list = " + list);   // [100, 1, 2, 4, 200]
        System.out.println("size = " + list.size());    // 5

        try {
            list.remove(-1);    // 按照下标删除
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        try {
            list.remove(5);     // 按照下标删除
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        System.out.println(list.remove((Integer) 100)); // true
        System.out.println("list = " + list);   // [1, 2, 4, 200]
        System.out.println("size = " + list.size());    // 4


        System.out.println(list.remove((Integer) 100)); // false
        System.out.println("list = " + list);   // [1, 2, 4, 200]
        System.out.println("size = " + list.size());    // 4

        System.out.println(list.get(0));    // 1
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        try {
            list.get(4);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }
        System.out.println(list.get(3));    // 200

        System.out.println(list.set(3, 2000));  // 200
        System.out.println("list = " + list);   // [1, 2, 4, 2000]
        System.out.println("size = " + list.size());    // 4

        System.out.println(list.isEmpty());     // false
        list.clear();
        System.out.println(list.isEmpty());     // true

//        // 确保会发生扩容
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//        list.add(8);
//        list.add(9);
//        list.add(10);
//        System.out.println("list = " + list);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
//        System.out.println("size = " + list.size());    // 10
//        list.add(11);   // 一定会发生扩容
//        System.out.println("list = " + list);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
//        System.out.println("size = " + list.size());    // 11
//
//        list.add(1);
//        System.out.println("list = " + list);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
//        System.out.println("size = " + list.size());    // 12
//        System.out.println(list.indexOf(1));    // 打印 0
//        System.out.println(list.lastIndexOf(1));    // 打印 11
//
//        System.out.println(list.indexOf(99));   // -1
//        System.out.println(list.lastIndexOf(99));   // -1
//
//        System.out.println(list.contains(1));   // true
//        System.out.println(list.contains(99));  // false

//        List list2 = new ArrayList();
//        System.out.println("list = " + list);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
//        System.out.println("list.size = " + list.size());    // 12
//        System.out.println("list2 = " + list2);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
//        System.out.println("list2.size = " + list2.size());    // 12
//
//        List list3 = list;
//        list3.set(0, 1000);
//        System.out.println("list = " + list);   // [1000, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
//        System.out.println("list2 = " + list2);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
//        System.out.println("list3 = " + list3);   // [1000, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1]
    }
}

