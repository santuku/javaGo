package myArrayList;


import java.util.Arrays;

public  class ArrayList implements List{
  private int[] array;
  private int  size;

    public ArrayList() {
        array = new int[10];
        size = 0;
    }


    public void ensureCapacity(int capacity){
        //检查是否需要扩容
        if(this.array.length >= capacity){
            return;
        }
        int[] newArray = new int[capacity];
        for (int i = 0; i < size; i++) {
           this.array[i] = newArray[i];
        }
        this.array = newArray;//不再关联array,而是newArray
    }

    @Override
    public boolean add(Integer e){
        if(array.length == size){
            ensureCapacity(array.length * 2);
        }
        array[size++] = e;
        return true;
    }

    @Override
    public void add(int index,Integer e) {

        if(index < 0 ||index  > size){
            throw new IndexOutOfBoundsException("不合法的下标" + index);
        }

        if(array.length == size){
            ensureCapacity(array.length * 2);
        }

        //元素后移
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        //TODO 不太懂
        array[index] = e;
        size++;
    }

    @Override
     public  boolean remove(Integer e){
         int index = indexOf(e);
         if(index != -1){
             remove(index);
             return true;
         }else{
             return false;
         }
     }

     @Override
     public Integer remove(int index){
        //合法下标是[0,size - 1]
         if(index < 0 || index >= size){
             throw new IndexOutOfBoundsException("不合法的下标" + index);
         }

        int e = array[index];
        for(int i = index + 1;i <= size - 1;i++){
            array[i - 1] = array[i];
        }
        size--;
        return e;
     }

      @Override
      public  Integer get(int index){
          if(index < 0 || index >= size){
              throw new IndexOutOfBoundsException("不合法的下标" + index);
          }
          return array[index];
     }


       @Override
      public  Integer set(int index,Integer e){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("不合法的下标" + index);
         }
         Integer old = array[index];
         array[index] = e;
         return old;
      }

        @Override
        public void clear(){
            Arrays.fill(array,-1);//调用Arrays的类方法，用-1代表无效值
            size = 0;
        }

        @Override
        public int size(){
            return size;
        }

        @Override
        public boolean isEmpty(){
            return size == 0;
        }

        @Override
        public boolean contains(Integer e){
            return indexOf(e) != -1;
        }

        @Override
        public  int  indexOf(Integer e){
            for (int i = 0; i < size; i++) {
                if(array[i] == e){
                    return i;
                }
            }
            return -1;
         }

       @Override
       public int lastIndexOf(Integer e){
            for (int i = size -1; i >= 0 ; i--) {
                if(array[i] == e){
                    return i;
                }
            }
            return -1;
      }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}



