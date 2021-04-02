package heap0320;

public class Heap{
    //1.这里建立一个大根堆
    public static void shiftDown(int []arr,int index){
        int parent = index;//parent是从最后一个非叶子节点开始，index变量使得代码看起来更加直观
        int child = 2 * parent  + 1;
        int size = arr.length;
        while(child < size){
            if(child + 1 <size && arr[child + 1] > arr[child]){
                child = child + 1;//两个兄弟节点进行比较，择其大者
            }
            if(arr[parent] < arr[child]){
                int temp = arr[parent];
                arr[parent] = arr[child];
                arr[child] = temp;
            }else{
                break;
            }
            //更新节点
            parent = child;
            child = 2 * parent + 1;
        }
    }

    public static void heapify(int []arr ){
        for(int index = (arr.length - 1)/2;index >= 0;index--){
            shiftDown(arr,index);
        }
    }

    //2.堆的插入
    public static void shiftUp(int []arr,int index){
        int child = index;
        int parent = (child - 1)/2;
        //child为0的时候，已经调整到了最上面
        while(child > 0){
            if(arr[child] > arr[parent]){
                int temp = arr[child];
                arr[child] = arr[parent];
                arr[parent] = temp;
            }else{
                break;
            }
            //更新节点
            child = parent;
            parent = (child - 1) / 2;
        }
    }
    int[] arr = new int[100];
    int size = 0;

    public void offer(int insertVal){
        if(size >= arr.length){
            //这里插入失败是因为数组已经满了，可以实现扩容
            return;
        }
        arr[size] = insertVal;
        size ++;
        shiftUp(arr,size - 1);
    }
    //基于向上调整的方法建堆，循环插入数组
    public void heapify2(int[] arr){
        for(int x : arr){
            offer(x);
        }
    }
    //3.堆的删除,一般都是删除堆顶元素
    public Integer poll(){
        if(size == 0){
            return null;
        }
        //堆顶元素(0号)与堆尾元素(size -1号)进行交换
        int temp = arr[size - 1];
        arr[size - 1] = arr[0];
        arr[0] = temp;
        //删除元素
        size --;
        shiftDown(arr,0);
    }
    //4.获取堆顶元素
    public Integer peek(){
        if(size == 0){
            return null;
        }
        return arr[0];
    }
}
