package java_0202;

import java.util.Scanner;
//有些没写输出语句
public class Main {
    public static Node createList() {
        //static:在main方法中被调用，
        // 静态成员无法访问非静态成员。
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        a.next = b;//？？？
        b.next = c;
        c.next = d;
        d.next = null;
        return a;//头结点可以表示该链表
    }

    public static void main(String[] args) {
        Node head = createList();

        //1.遍历链表，打印链表的每一个元素
//        for (Node x = head; x != null; x = x.next) {
//            System.out.println(x.item);
//        }
        //2.遍历链表，找到链表的最后一个节点
//        Node x = head;
//         while( x != null && x.next != null){
//             head =  head.next;
//        }
         //3.遍历链表，找到链表的倒数第二个节点
        //.next.next是null
//        Node x = head;
//        while(x.next.next != null && x != null && x.next != null ){
//            x = x.next;//将x设为x.next来访问下一个元素
//        }

        //4.取链表的第N(4)个结点
//        Node x = head;
//        for(int n = 0;n < 4;n++){
//            x = x.next;
//        }
        //5.获取链表的长度
        int count = 0;
        Node x = head;
        //count不能定义在while内部
        while(x != null){
            count++ ;
            x = x.next;
        }
        System.out.println(count);
        //6.遍历链表，查找链表上是否存在某个元素
//        int find = 6;
//        Node x = head;
//        for(;x != null;x = x.next) {
//            if(x.item == find){
//                break;
//            }
//        }
//        if(x != null){
//            System.out.println("找到了");
//        }else{
//            System.out.println("没找到");
//        }
    }
}
