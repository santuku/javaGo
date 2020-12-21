 public class Array1{
    public static void main(String[] args) {
        //1.求数组的平均值
        /*实现一个方法 avg, 以数组为参数,
        求数组中所有元素的平均值. 注意结果是一个浮点型的值*/
        int[] arr = new int[]{1,2,3,4};
     int[] arr1= new int[100];
     int[] arr2 = {1,2,3,47,8};
//        double average =  avg(arr);
//        System.out.println(average);
        //2.数组所有元素之和
        //System.out.println(sum(arr));
        //3.改变原有数组元素的值
        /*实现一个方法 transform, 以数组为参数, 循环将数组中的每个元素 乘以 2 ,
       并设置到对应的数组元素上. 例如 原数组为 {1, 2, 3}, 修改之后为 {2, 4, 6}*/
       // transform(arr);
        //4.如何打印数组呢？  这一句打印出   [I@1540e19d
        //printArray(arr2);
        //5.创建数组，并且赋初始值
        /*创建一个 int 类型的数组, 元素个数为 100,
        并把每个元素依次设置为 1 - 100*/
//        for (int i = 0; i < arr1.length; i++) {
//            arr1[i] = i + 1;
//        }
//       printArray(arr1);
        //6.数组转字符串
        /*和4类似，主要用到了“字符串拼接”*;可以用Arrays.toString*/
        //System.out.println(toString(arr));
        //7.数组拷贝/*和2类似：
        // 标准库中有：Arrays.copyOf(arr, arr.length)函数*/
    }

     public static  String toString(int[] arr) {
            String atFirst = "[";//这个变量名可以用ret来代替
         for (int i = 0; i < arr.length; i++) {
             atFirst += arr[i];
             if(i != arr.length - 1){
                 atFirst  += ",";
             }
         }
         atFirst += "]";
         return atFirst;
     } 

     public static void printArray(int[] arr) {
         System.out.print("[");
         for (int i = 0; i < arr.length - 1; i++) {
             System.out.print(arr[i] + ",");
         }
         System.out.println( arr[arr.length -1] + "]");
     }

     public static void transform(int[] arr) {
         int[] transform = new int[arr.length];
         for (int i = 0; i < arr.length ; i++) {
             transform[i] = arr[i] * 2;
         }
         for (int x:transform) {
             System.out.print(x+" ");
         }
     }

     public static int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length ; i++) {
            sum+= arr[i];
        }
        return sum;
    }

    public static double avg(int[] arr) {
        int sum = 0;
        for(int i = 0;i < arr.length;i++){
           sum += arr[i];
        }
        double average= (double)sum / arr.length;
        return average;
    }
}
