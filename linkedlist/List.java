package linkedlist;

public interface List {
    boolean add(Integer e);
    void add(int index,Integer e);//没有返回值

    boolean remove(Integer e);
    Integer remove(int index);

    Integer get(int index);
    Integer set(int index,Integer e);//返回元素

    void clear();
    int size();
    boolean isEmpty();

    boolean contains(Integer e);
    int  indexOf(Integer e);
    int lastIndexOf(Integer e);
}
