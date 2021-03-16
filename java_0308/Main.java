package java_0308;

class ListNode {
    int val = 0;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Main {
    // 删除链表中的所有元素
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 删除操作是需要找到当前节点的前一个节点的.
        ListNode prev = head; // 待删除的节点的前一个节点
        ListNode cur = head.next; // 待删除节点
        for (;cur != null;) {
            if (cur.val == val) {
                // 如果找到了值为 val 的节点
                // 就需要删除这个节点
                prev.next = cur.next;
                cur = prev.next;
            } else {
                // 如果没找到, 更新 prev 和 cur 的位置
                prev = prev.next;
                cur = cur.next;
            }
        }
        // 删除操作也需要单独考虑待删除元素是头结点的情况.
        if (head.val == val) {
            head = head.next;
        }
        return head;
    }

    // 链表逆置
    public ListNode reverseList(ListNode head) {
        // 写任意代码的时候, 都要记得把特殊情况都得处理到位
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            // 链表里只有一个节点
            return head;
        }
        // 处理一般情况
        ListNode newHead = null;
        ListNode prevNode = null;
        ListNode curNode = head;
        while (curNode != null) {
            // 进入循环的时候, 需要先设定好 nextNode 的位置
            ListNode nextNode = curNode.next;
            if (nextNode == null) {
                // curNode 就指向了链表的最后一个节点.
                // 也就是反转后的新链表的头结点
                newHead = curNode;
            }
            // 掰道岔
            curNode.next = prevNode;
            // 更新引用的位置
            prevNode = curNode;
            curNode = nextNode;
        }
        return newHead;
    }

    public static void print(ListNode head) {
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.println(cur.val);
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        Main solution = new Main();
        ListNode result = solution.removeElements(head, 1);
        print(result);
    }
}