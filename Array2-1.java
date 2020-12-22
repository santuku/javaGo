import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeArray.reverse;

public class Array2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //1.找数组中最大的元素
        int[] arr = new int[]{4,6,8,10,12,14,15,17,58};
        int maxNum = findMaxNum(arr);
        System.out.println(maxNum);
        //2（1）.查找数组中的指定元素
        System.out.println("请输入您要查找的数字：");
        int toFind = sc.nextInt();
        System.out.println(findNum(arr,toFind));
        // (2)用二分查找来查找数组中的指定元素
        System.out.println(binarySearch(arr,toFind));
        //3.检查数组的有序性,假设为升序
        System.out.println(checkIsOrdered(arr));
        //4.数组排序（冒泡排序）
        bubbleSort1(arr);
        bubbleSort2(arr);//区分一个已排区间和未排区间
    }

    public static void bubbleSort2(int[] arr) {
        //分成了一个已排序区间和待排序区间[0,bound)和[bound,arr.[length - 1])
        for(int bound = 0;bound < arr.length;bound ++){
            for(int cur = arr.length - 1;cur > bound; cur--){//也可以用++的方式来写
                if(arr[cur] < arr[cur -1]) {
                    int temp = arr[cur - 1];
                    arr[cur] = temp;
                    arr[cur - 1] = arr [cur] ;
                }
            }
        }
    }

    }

    public static int[] bubbleSort1(int[] arr) {
        for (int i = 0;i < arr.length - 1; i ++){
            for(int j= 0;j < arr.length - i ;j ++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static boolean checkIsOrdered(int[] arr){
        //如果不论升序还是降序，怎么实现？
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] < arr[i+1]){
                return true;
            }
        }
        return false;
    }

    private static int binarySearch(int[] arr, int toFind) {
        //抽象！
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        //用另外两个变量给新的变量赋值  抽象出用low high mid表达的方式
        //while循环的使用，可以用for循环改写吗？
        //对 二分查找  是否理解透彻？
        // 递归实现
        while(low < high){
            if(toFind == mid){
                return toFind;
            }else if(toFind < mid){//向左查找
                high =  mid - 1;
            }else{//向右查找
                low = mid +1;
            }
        }
        return -1;//这个数组里没有这个数字，没有找到
    }

    private static int findNum(int[] arr, int toFind) {
        for (int i = 0; i < arr.length; i++) {
            if(toFind == arr[i]){
                return i;
            }
        }
        return -1;
    }

    public static int findMaxNum(int[] arr) {
        int compare = arr[0];
        for(int i = 0;i < arr.length;i++ ){
            if(arr[i] > compare){
                compare = arr[i];
            }
        }
        return compare;
    }
}
