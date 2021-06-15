package MyTreeSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyTreeSet implements Set<Integer> {
    private TreeNode root;
    private int size;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Comparable<Integer> comparable = (Comparable<Integer>) o;

        TreeNode cur = root;
        while(cur != null){
            int cmp = comparable.compareTo(cur.key);
            if(cmp == 0){
                return true;
            }else if(cmp < 0){
                cur = cur.left;
            }else{
                cur = cur.right;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }


    @Override
    public boolean add(Integer integer) {
        if(root == null){
            root = new TreeNode(integer);
            size++;
            return true;
        }

        TreeNode parent = null;
        TreeNode cur = root;

        int cmp = 0;
        while(cur != null){
            cmp = integer.compareTo(cur.key);
            if(cmp == 0){
                return false;
            }else if(cmp < 0){
                parent = cur;
                cur = cur.left;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }

        if(cmp < 0){
            parent.left = new TreeNode(integer);
        }else{
            parent.right = new TreeNode(integer);
        }
        size++;
        return true;
    }


    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }



    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }



}
