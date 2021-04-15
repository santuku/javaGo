package StringMethod;

public class TestIndexOf {
    public static int myIndexOf(String src,String dest) {
        //先进行一些条件判断
        char[] srcArray = src.toCharArray();
        char[] dstArray = dest.toCharArray();
        int srcLen = srcArray.length;
        int dstLen = dstArray.length;

        for (int i = 0; i < srcLen; i++) {
            boolean find = false;
            if (srcArray[i] == dstArray[0]) {
                int equalCount = 0;
                for (int j = 0; j < dstLen; j++) {
                    if (srcArray[i + j] == dstArray[j]) {
                        equalCount++;
                    }
                }
                if (equalCount == dstLen) {
                    find = true;
                }
                if (find) {
                    return i;
                }
            }
            return -1;
        }
        return 1;
    }

}
