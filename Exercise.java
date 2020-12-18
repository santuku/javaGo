import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
Ss











import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Scanner;
public class Exercise {
    public static void main(String[] args) {//String[] args是什么意思
        //求0-100中数字9出现的次数，如果是0-1000呢
       //int count = calRangeNineCount(1,1000);
        //System.out.println(count);
        //输出 1000 - 2000 之间所有的闰年
         //printAllLeapYear(1000,2000);
        //输出0-100的素数
        // printPrimeNumber(0,100);
        //判断一个数字是否是素数
         Scanner  scanner = new Scanner(System.in);
         int year = scanner.nextInt();
      System.out.println(isPrimeNumber(year));
    //根据输入的年龄, 来打印出当前年龄的人是少年(低于18), 青年(19-28), 中年(29-55), 老年(56以上)
//        Scanner  scanner = new Scanner(System.in);
//         int age = scanner.nextInt();
//         manLifeStage(age);
    }
    public static int calRangeNineCount(int begin,int end){
        int count = 0;
        for(int i = begin;i <= end;i++){
            count += calCurrentNumNineCount(i);
        }
        return count;
    }
    public static int calCurrentNumNineCount(int num){
        int count = 0;
        int digits = num % 10;//得到个位数字
        if(digits == 9){
            count++;
        }
        int tens = num / 10 % 10;
        if(tens == 9){
            count++;
        }
        return count;
    }
    public static void  printAllLeapYear(int begin,int end){
        for(int i = begin;i <= end;i++){
            if( isLeapYear(i) == 1){
                System.out.println(i                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            );
           }
        }
        return;
    }
    public static int isLeapYear(int year){
        if(year % 100 == 0){
            //世纪闰年
            if(year % 400 ==0){
                return 1;
                //System.out.println("是闰年");
            }
        }else{
            //普通闰年
            if(year % 4 == 0){
                return 1;
                //System.out.println("是闰年");
            }else{
               // System.out.println("不是闰年");
                return 0;
            }
        }
        return 0;
    }
    public static void printPrimeNumber(int begin,int end){
        for (int i = begin;i <= end;i++){
            if(isPrimeNumber(i)){
                System.out.println(i);
            }
        }
    }
    public static boolean isPrimeNumber(int num){
        //注意是从0开始，所以要注意边界值
        if(num == 1 || num == 0){
            return false;
        }
        for(int i = 2;i < num / 2 ;i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
   public static void manLifeStage(int age){
        if(age < 18){
            System.out.println("是少年");
        }else if(age >= 19 && age <= 28){
            System.out.println("是中年");
        }else if(age >= 29 && age <= 55){
            System.out.println("是中年");
        }else if(age >= 56){
            System.out.println("是老年");














            Ss

   }
}
