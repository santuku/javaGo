package StringMethod;

import java.util.Arrays;

import java.util.Arrays;
public class Split{
    public static void main(String[] args) {
        String a="198.163.01";
        Split(a,'.');

    }
    public static void Split(String num,char target){
        int count=0;
        //统计字符串中的目标分割符数目
        for(int i=0;i<num.length();i++){
            if(target==num.charAt(i)){
                count++;
            }
        }
        //创建一个字符串类型数组
        String[] result=new String[count+1];
        Arrays.fill(result, "");
        char temp=' ';
        int t=0;
        //截取字符串
        for(int i=0;i<num.length();i++){
            if(target!=num.charAt(i)){
                temp=num.charAt(i);
                result[t]+=temp;
            }
            //若遇到分隔符则字符串数组后移
            else
                t++;
        }
        //打印输出
        for (String s : result) {
            System.out.println(s);
        }
    }
}


