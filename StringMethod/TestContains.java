package StringMethod;

public class TestContains {

    public static boolean myContains(String src,String dst){
        char[] srcArray = src.toCharArray();
        char[] dstArray = dst.toCharArray();
        int srcLen = srcArray.length;
        int dstLen = dstArray.length;
        for (int i = 0; i < srcLen ; i++) {
            boolean find = false;
            if(srcArray[i] == dstArray[0] && (i + dstLen <srcLen)){
                int equalCount = 0;
                for(int j = 0;j < dstLen ;j++){
                    if(srcArray[i + j] == dstArray[j]){
                        equalCount ++;
                    }
                    if(equalCount == dstLen){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(myContains("hello", "ell"));
    }
}
