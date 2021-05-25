package myArrayList;


public class ArrayListIterator implements Iterator {
    private ArrayList list;//要迭代遍历的那个顺序表
    private int index;//在顺序表的位置

    ArrayListIterator(ArrayList list){
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return list.get(index++);
        //next是获得当前下标并且移动到下一个
    }
    @Override
    public void remove(){

    }
}
