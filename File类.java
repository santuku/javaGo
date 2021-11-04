package 输入输出;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.io.IOException;

public class File类 {
    public static void main(String[] args) throws IOException {
        File file = new File(".");
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getAbsoluteFile().getParent());

        File tmpFile = File.createTempFile("aaa",".txt",file);
        tmpFile.deleteOnExit();//当JVM退出时删除该文件
        //以系统当前时间作为新文件名来创建文件
        File newFile = new File(System.currentTimeMillis() + "");
        System.out.println("newFile对象是否存在：" + newFile.exists());
        //以指定的newFile对象来创建一个文件
        newFile.createNewFile();
        //以newFile对象创建一个目录，因为newFile已经存在
        //方法返回false，无法创建该目录
        newFile.mkdir();
        String[] fileList = file.list();
        System.out.println("====当前路径下所有文件和路径如下====");
        for(String fileName : fileList){
            System.out.println(fileName);
        }

        File[] roots = File.listRoots();
        System.out.println("====系统所有根路径如下====");
        for(File root : roots){
            System.out.println(root);
        }

    }
}
