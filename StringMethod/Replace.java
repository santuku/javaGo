package StringMethod;

public class Replace {
    //只能替换一次
        public static String myreplace(String str, String a, String b) {
            //将要操作的字符串转化为字符数组；
            char[] val = str.toCharArray();
            char[] s1 = a.toCharArray();
            char[] s2 = b.toCharArray();
            //记录字符串的长度
            int len = str.length();
            //创建一个存储新的值的字符数组 长度为 原来的长度加上要替换的减去目标字符
            char[] cur = new char[len + s2.length - s1.length];
            //寻找要替换的位置的下标
            //index用来遍历s1
            int index = 0;
            int i = 0;
            int x = 0;
            for (i = 0; i < val.length; i++) {
                if (val[i] == s1[index]) {
                    if (index == s1.length - 1) {
                        break;
                    }
                    index++;
                } else {
                    if (val.length - i < s1.length) {
                        break;
                    }
                    //如果不符合 将index赋值为0 继续下一次的遍历
                    index = 0;
                }
            }
            //用x来存储s1开始的下标
            x = i - s1.length + 1;

            //第一段进行赋值
            for (int k = 0; k < x; k++) {
                cur[k] = val[k];
            }
            //替换部分赋值
            //用m来存储s2的下标
            int m = 0;
            for (int k = x; k < x + s2.length; k++) {
                cur[k] = s2[m];
                m++;
                //如果m=s2的长度 说明执行完毕
                if (m == s2.length) {
                    break;
                }
            }
            //第三段赋值
            int n = x + s1.length;
            for (int k = x + s2.length; k < cur.length; k++) {
                cur[k] = val[n];
                n++;
                if (n == val.length) {
                    break;
                }
            }

            String s=new String(cur);
            System.out.println(s.toString());
            return s;
        }
        public static void main(String[] args) {
            String s="hello world";
            String s1="rl";
            String s2="o";
            //System.out.println(s.replace(s1,s2).toString());
            myreplace(s,s1,s2);
        }
    }

