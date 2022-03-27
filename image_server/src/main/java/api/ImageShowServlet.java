package api;

import dao.Image;
import dao.ImageDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageShowServlet extends HttpServlet{
//    static private HashSet<String> whiteList = new HashSet<>();
//
//    static{
//        whiteList.add("http://81.71.135.98:8080/image_server/index1.html");
//    }
//
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //refer表示当前请求的上个页面的地址
//        String referer = req.getHeader("Referer");
//        if(!whiteList.contains(referer)){
//            resp.setContentType("applicaton/json;charset:utf-8");
//            resp.getWriter().write("{ \"ok\": false, \"reason\": \"未授权的访问\" }");
//            return;
//        }
        //1.解析出imageId
        req.setCharacterEncoding("UTF-8");
        String imageId = req.getParameter("imageId");
        if(imageId == null || imageId.equals("")){
            resp.setContentType("application/json; charset: utf-8 ");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().write("{ \"ok\": false, \"reason\": \"imageId 解析失败\" }");
            return;
        }
        ImageDao imageDao = new ImageDao();
        //2.查找数据库，得到图片的属性
        Image image = imageDao.selectOne(Integer.parseInt(imageId));
        //3.根据路径打开文件，读取其中内容，写入到响应对象中
        resp.setContentType(image.getContentType());
        File file = new File(image.getPath());

        //图片是二进制文件，使用字节流的方式读取文件
       //返回一个输出流对象，用来向客户发送文本数据
        OutputStream outputStream = resp.getOutputStream();
        //使用File对象创建流对象/通过FileInputStream的构造方法打开与实际文件的连接创建一个FileInputStream
        FileInputStream fileInputStream = new FileInputStream(file);
        //1k大小的缓冲区，提高读取效率
        byte[] buffer = new byte[100000];
        while(true){
            //FileInputStream读取字节数据
            int len = fileInputStream.read(buffer);
            if(len == -1){
                //文件读取结束
                break;
            }
            //此时已读到一部分数据，放到buffer里，把buffer中的内容写到响应对象中
            outputStream.write(buffer);
        }
        //使用流要及时关闭
        fileInputStream.close();
        outputStream.close();
    }
}
