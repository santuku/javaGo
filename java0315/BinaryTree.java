package java0315;
import java.util.ArrayList;
import java.util.List;

class Node{
    String val;
    Node left;
    Node right;

    public Node(String val){
        this.val = val;
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val){
        this.val = val;
    }
}
public class BinaryTree {
    public static Node build(){
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        e.left = g;
        c.right = f;
        return a;
    }

    //先序遍历
    public static void preOrder(Node root){
        if(root == null){
            return;//返回值是void类型，用return;中止
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(Node root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val + " ");
    }
    //求二叉树结点的个数：用递归的方法
    public static int length(Node root){
        if( root == null){
            return 0;
        }
        return 1 + length(root.left ) +length(root.right);
    }

    //求叶子结点的个数
    public static int leafSize = 0;
    public static void getLeafSize(Node root){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            leafSize++ ;
        }
        getLeafSize(root.left);
        getLeafSize(root.right);
    }

    //求第k层结点的个数,(i层最多有2的（i-1）次方个结点)
    public static int getKLevelSize(Node root,int k){
        if(root == null || k < 0){
            return 0;
        }
        if(k == 1){
            return 1;
        }
        return getKLevelSize(root.left,k - 1) + getKLevelSize(root.right,k -1);
    }
    //求二叉树的高度：较高一棵子树的高度加1
    public static int height(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + (leftHeight > rightHeight ? leftHeight :rightHeight);
    }

    public static Node find(Node root,String toFind){
        if(root == null ){
            return null;
        }
        if(root.val.equals(toFind)){
            return root;
        }
        Node ret = find(root.left,toFind);
        if(ret != null){
            return ret;
        }
        return find(root.right,toFind);
    }

    public static void main(String[] args) {
         Node root = build();
//        preOrder(root);
//        inOrder(root);
//        postOrd er(root);
//        System.out.println(length(root));
//        System.out.println(leafSize);
//        System.out.println(getKLevelSize(root,3));
//        System.out.println(height(root));
        System.out.println(find(root,"F"));
    }
}
