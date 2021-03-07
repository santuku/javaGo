package java_0307;

import java_0202.Node;

public class Main {

        public static Node createList() {
            Node a = new Node(1);
            Node b = new Node(2);
            Node c = new Node(3);
            Node d = new Node(4);
            a.next = b;
            b.next = c;
            c.next = d;
            d.next = null;
            return a;
        }

        // 带傀儡节点的链表.
        // 本质的区别就在于 傀儡节点 中的值是不使用的.
        // 里面的链表认为长度是 4
        public static Node createListWithDummy() {
            Node dummy = new Node(0);
            Node a = new Node(1);
            Node b = new Node(2);
            Node c = new Node(3);
            Node d = new Node(4);
            dummy.next = a;
            a.next = b;
            b.next = c;
            c.next = d;
            d.next = null;
            return dummy;
        }
    // 带傀儡节点和不带傀儡节点, 是两种风格迥异的链表.
    // 不要尝试用一套代码来解决两种链表的问题.
    // 必须要分别实现.

    // 遍历一个不带傀儡节点的链表
    public static void print(Node head) {
        for (Node cur = head; cur != null; cur = cur.next) {
            System.out.println(cur.item);
        }
    }

    // 遍历一个带傀儡节点的链表
    public static void printWithDummy(Node head) {
        for (Node cur = head.next; cur != null; cur = cur.next) {
            System.out.println(cur.item);
        }
    }

    // 尾插一个节点
    public static Node insertTail(Node head, int val) {
        Node newNode = new Node(val);
        if (head == null) {
            return newNode;
        }
        // 1. 先找到末尾节点
        Node prev = head;
        while (prev.next != null) {
            prev = prev.next;
        }
        // 循环结束,  prev 就是最后一个节点了~
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }

    // 删除节点, 此处是按照值来删除
    public static Node remove(Node head, int value) {
        if (head == null) {
            return null;
        }
        if (head.item == value) {
            // 要删除的节点就是头结点
            head = head.next;
            return head;
        }
        // 1. 先找到 val 这个值对应的位置
        //    同时也要找到 val 的前一个位置
        Node prev = head;
        while (prev != null
                && prev.next != null
                && prev.next.item != value) {
            prev = prev.next;
        }
        // 循环结束之后, prev 就指向待删除节点的前一个节点了.
        if (prev == null || prev.next == null) {
            // 没有找到值为 val 的节点
            return head;
        }

        // 2. 真正进行删除了, toDelete 指向要被删除的节点
        Node toDelete = prev.next;
        prev.next = toDelete.next;
        return head;
    }

    // 删除节点, 按照位置来删除.
    public static Node remove(Node head, Node toDelete) {
        if (head == null) {
            return head;
        }
        if (head == toDelete) {
            // 要删除的就是头结点
            head = head.next;
            return head;
        }
        // 1. 先需要找到 toDelete 的前一个节点
        Node prev = head;
        while (prev != null && prev.next != toDelete) {
            prev = prev.next;
        }
        if (prev == null) {
            // 没找到
            return head;
        }
        // 2. 进行删除
        prev.next = toDelete.next;
        return head;
    }

    public static Node remove2(Node head, Node toDelete) {
        if (head == null) {
            return head;
        }
        if (head == toDelete) {
            head = head.next;
            return head;
        }
        Node nextNode = toDelete.next;
        toDelete.item = nextNode.item;
        toDelete.next = nextNode.next;
        return head;
    }

    public static int size(Node head) {
        int size = 0;
        for (Node cur = head; cur != null; cur = cur.next) {
            size++;
        }
        return size;
    }

    // 给定节点下标来删除.
    public static Node remove3(Node head, int index) {
        if (index < 0 || index >= size(head)) {
            return head;
        }
        // 如果 index 为 0, 意味着要删除头结点(一会再考虑)
        if (index == 0) {
            head = head.next;
            return head;
        }
        // 1. 还是要先找到待删除节点的前一个位置. index - 1 这个节点就是前一个位置
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        // 循环结束之后, prev 就指向了待删除节点的前一个位置
        // 2. 真正进行删除
        Node toDelete = prev.next;
        prev.next = toDelete.next;
        return head;
    }

    // 针对带傀儡节点的链表, 删除指定元素
    public static void removeWithDummy(Node head, int val) {
        // 此时就不必考虑到 head 引用修改的问题, 也不必单独考虑删除第一个节点的事情了.
        Node prev = head;
        while (prev != null && prev.next != null
                && prev.next.item != val) {
            prev = prev.next;
        }
        // 当这个循环结束的时候, 要么是 prev 到达了链表末尾, 要么是找到了 val 匹配的值
        if (prev == null || prev.next == null) {
            // 认为没有找到对应的节点
            return;
        }
        // 找到了对应的节点
        // 如果要删除的 val 正好是第一个节点, 此时 prev 正好是指向 head 的.
        Node toDelete = prev.next;
        prev.next = toDelete.next;
        return;
    }

    // 针对不带傀儡节点的链表, 进行尾删操作
    public static Node removeTail(Node head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            // 链表里只有一个节点, 尾删的节点就是这个 head 本身
            // 此时删除该节点之后, 这个链表就变成空链表了.
            return null;
        }
        // 一般的情况, 需要找到尾部节点的前一个节点
        Node prev = head;
        Node toDelete = prev.next;
        // 这个条件写起来有点复杂, 把代码等价转换一下
//        while (prev != null && prev.next != null && prev.next.next != null) {
//            prev = prev.next;
//        }
        while (prev != null && prev.next != null) {
            toDelete = prev.next;
            if (toDelete.next == null) {
                break;
            }
            prev = prev.next;
        }
        // 接下来就删除这个节点即可
        // 由于 toDelete 已经是最后一个节点了, 它的 next 一定是 null
        // 所以下面的代码也能写成:
//        prev.next = null;
        prev.next = toDelete.next;
        return head;
    }

    // 返回的链表是一个不带傀儡节点的链表
    public static Node arrayToLinkedList(int[] array) {
        // 遍历数组, 把元素依次进行尾插即可.
        // 每次尾插, 都需要知道当前链表的末尾节点.
        // 每次重新找这个末尾节点, 太麻烦了~
        // 可以直接使用一个引用把尾节点给记住.

        // head 就是头结点的引用. 初识情况下, 链表是空的.
        Node head = null;
        // tail 也是空的.
        Node tail = null;
        for (int x : array) {
            Node node = new Node(x);
            // 把 node 进行尾插
            // 需要判定当前链表是否为空
            if (head == null) {
                head = node;
                tail = node;
            } else {
                // 链表不为空的时候, 再进行新的插入, 就不必管 head 了, 直接操作 tail 即可
                tail.next = node;
                // 一旦插入完成, 新节点就成了 tail, 需要更新 tail 的指向
                tail = tail.next;
            }
        }
        return head;
    }

    public static Node arrayToLinkedList2(int[] array) {
        // 使用更简单的方式完成
        // 如果我们创建一个带傀儡节点的链表的话, 后续的尾插操作, 就不需要分两种情况来讨论了.
        // 此时的 head 就是指向傀儡节点
        Node head = new Node(0);
        // 也创建一个 tail 引用保存链表的尾巴
        Node tail = head;

        for (int x : array) {
            // 此时就把两种插入操作合并成一种了.
            Node node = new Node(x);
            tail.next = node;
            tail = tail.next;
        }
        // 代码的要求是返回的结果是一个不带傀儡节点的链表.
        // 如果返回 head, head 指向的是傀儡节点, 不符合要求.
        // 返回 head.next 就正好合适了~
        return head.next;
    }
    public static void main(String[] args) {
//        Node head = createList();

        // 创建新节点
//        Node newNode = new Node(100);

        // 把节点插入到 1 和 2 之间.
//        Node one = head;
//        // 1) 先把 newNode 的 next 指向 one 的 next
//        newNode.next = one.next;
//        // 2) 再把 one 的 next 指向 newNode
//        one.next = newNode;

        // 把节点插入到链表头部
        // 1) 先让新节点的 next 指向 head
//        newNode.next = head;
        // 2) 再让 head 指向新节点
//        head = newNode;

        // 创建带傀儡节点的链表
//        Node head = createListWithDummy();
//
//        Node newNode = new Node(100);

        // 1. 往中间某个位置插入. 需要知道待插入位置的前一个位置.
        //    例如, 还是往 1 和 2 之间插入
        //    prev 就是指向 1 的位置. prev 名字的由来, 表示前一个元素
//        Node prev = head.next;
//        newNode.next = prev.next;
//        prev.next = newNode;

        // 2. 往链表头部插入. (由于是带傀儡节点, 其实是插入到 head 的后面)
//        Node prev = head;
//        newNode.next = prev.next;
//        prev.next = newNode;

//        Node head = createList();

//        Node head = null;
//        head = insertTail(head, 100);
//        print(head);

//        Node head = createList();
//        head = remove(head, 1);
//        // toDelete 指向 3 这个节点了.
////        Node toDelete = head.next.next;
//        print(head);

//        Node head = createListWithDummy();
//        removeWithDummy(head, 1);

        int[] array = {1, 2, 3, 4, 5};
        Node head = arrayToLinkedList(array);
        print(head);
    }
}
