package BST;

public class BST {
    private Node root;

    public BST(Node root) {
        root = null;
    }

    //1.查找操作
    public boolean search(Integer key){
        Node current = root;

        while(current != null){
            int cmp = key.compareTo(current.key);
            if(cmp == 0){
                return true;
            }else if(cmp < 0){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return false;
    }


    //2.插入操作
    public void insert(Integer key){
        if(root == null){
            root = new Node(key);
            return;
        }
        //始终保持parent就是current的双亲节点，要插入的话就要先找到母节点
        Node parent = null;
        Node current = root;

        int cmp = 0;
        while(current != null){
            cmp = key.compareTo(current.key);
            if(cmp == 0){
                throw new RuntimeException("BST中不允许出现相同的key");
            }else if(cmp < 0){
                parent =current;
                current = current.left;
            }else{
                parent = current;
                current = current.right;
            }
        }
        Node node = new Node(key);
        if(cmp < 0){
            parent.left = node;
        }else{
            parent.right = node;
        }
    }

    //3.删除操作
    public boolean remove(Integer key){
        Node parent = null;
        Node cur = root;

        while(cur != null){
            int cmp = key.compareTo(cur.key);
            if(cmp == 0){
                removeInternal(cur,parent);
                return  true;
            }else if(cmp < 0){
                parent = cur;
                cur = cur.left;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        return false;
    }

    private void removeInternal(Node node, Node parent) {
        /**
         * 四种情况：
         * 1.要删除的节点左右都为空；
         * 2.左不为空，右空；
         * 3.左空，右不空；
         * 4.左右都不空
         * */
        if(node.left == null && node .right == null){
            //只要parent 不为空,root就一定不是根节点
            if(node == root){
                root = null;
            }else if(node == parent.left){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(node.left != null && node.right == null){
            if(node == root){
                root = node.left;
            }else if(node == parent.left){
                parent.left = node.left;
            }else{
                parent.right = node.left;
            }
        }else if(node.right != null && node.left == null){
            if(node == root){
                root = node.right;
            }else if(node == parent.left){
                parent.left = node.right;
            }else{
                parent.right = node.right;
            }
        }else{
            //左右都不为空，使用替换的方法删除
            Node ghostParent = node;
            Node ghost = node.left;
            //从左边开始，一路朝右查找
            while(ghost.right != null){
                ghostParent = ghost;
                ghost = ghost.right;
            }

            //替换
            node.key = ghost.key;
            //删除ghost节点
            if(node == ghostParent){
                ghostParent.left = ghost.left;
            }else{
                ghostParent.right = ghost.left;
            }
        }
    }

}
