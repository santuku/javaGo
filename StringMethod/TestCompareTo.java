package StringMethod;


public class TestCompareTo {

    public static int myCompareTo(String s1,String s2) {
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        int s1Len = s1Array.length;
        int s2Len = s2Array.length;
        int shortOne;
        if (s1Len > s2Len) {
            shortOne = s2Len;
        } else {
            shortOne = s1Len;
        }
        for (int i = 0; i < shortOne; i++) {
            int ret = s1Array[i] - s2Array[i];
            if (ret > 0) {
                return 1;
            } else if (ret == 0) {
                return 0;
            } else {
                return -1;
            }
        }
        if(s1Len == s2Len){
            return 0;
        }else{
            return(s1Len - s2Len > 0 ? 1 : -1);
        }
    }

        public static void main(String[] args) {
            System.out.println(myCompareTo("hello", "ell"));
        }

}
