package api;

//todo 乱码情况：在out之前设置响应编码；service()抽象方法（doget dopost是service()方法的具体实现；调用几次执行几次），tomcat启动时init()执行一次，关闭tomcat服务时，再执行一次destroy()
//todo Servlet API 可以适用于任何通信协议（http协议的软件包和除了这个协议之外的其他软件包）

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Image;
import dao.ImageDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ImageServlet extends HttpServlet {
    //查看图片信息
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String imageId = req.getParameter("imageId");
       //通过url看imageId是否存在，存在的话就查看指定图片属性不存在就查看全部的
       if(imageId == null || imageId.equals("")){
           selectAll(req,resp);
       }else{
           selectOne(imageId,resp);
       }

    }


    private void selectAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        //1.创建一个ImageDao对象，查找数据库
        // TODO: 三层逻辑，用这个连接数据访问层
        ImageDao imageDao = new ImageDao();
        List<Image> images = imageDao.selectAll();
        //2.查找到的结果转换成json格式的字符串，写回给resp对象
        Gson gson =new GsonBuilder().create();
        //统一命名，可以一步到位的完成转换
        String jsonData = gson.toJson(images);
        resp.getWriter().write(jsonData);
    }



    private void selectOne(String imageId,HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        //1.创建ImageDao对象
        ImageDao imageDao = new ImageDao();
        Image image = imageDao.selectOne(Integer.parseInt(imageId));
        //2.使用gson把数据转成json格式，返回给响应对象
        Gson gson = new GsonBuilder().create();
        String jsonData = gson.toJson(image);
        resp.getWriter().write(jsonData);
    }

    //插入图片
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取图片的属性信息，存入数据库

        //FileItemFactory是一个接口,DiskFileItemFactory是实现类
        FileItemFactory factory = new DiskFileItemFactory();
        //文件上传,upload（解析器）对象进一步解析HTTP请求body中的内容
        ServletFileUpload upload = new ServletFileUpload(factory);

        //FileItem是一个文件上传项
        List<FileItem> items = null;
        try {
            //通过parseRequest解析form中的所有请求字段，并保存到items集合中
            //调用解析器解析上传数据
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
            resp.setContentType("application/json;charset = utf-8");//json格式的响应，需要把contentType设置成指定格式
            resp.getWriter().write("\"ok\":false,\"reason\":\"解析请求失败\"");
            return;
        }
        //要把FileItem中的属性提取出来，转换成Image对象，才能存到数据库
        FileItem fileItem = items.get(0);
        Image image = new Image();

        image.setImageName(fileItem.getName());
        image.setSize((int) fileItem.getSize());
        //手动获取一下当前日期，并转换成格式日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        image.setUploadTime(simpleDateFormat.format(new Date()));

        image.setContentType(fileItem.getContentType());
        image.setMd5(DigestUtils.md5Hex(fileItem.get()));
        //服务器中存储路径，自己构造
        image.setPath("./image/" + image.getImageName());
        //两个图片，内容不同名字相同，就可能出现上传失败的情况 改进方法：时间戳

        //存到数据库里,封装好的对象ImageDao
        ImageDao imageDao = new ImageDao();
        Image existImage = imageDao.selectByMd5(image.getMd5());
        imageDao.insert(image);

        //2.获取图片的内容信息并写入磁盘文件
        if (existImage == null) {
            //通过File对象：定义文件路径，指定上传的位置
            File file = new File(image.getPath());
            try {
                //上传
                fileItem.write(file);
            } catch (Exception e) {
                e.printStackTrace();
                resp.setContentType("application/json; charset=utf-8");
                resp.getWriter().write("{ \"ok\": false, \"reason\": \"写磁盘失败\" }");
                return;
            }
        }
        //3.给客户端返回结果数据
//        resp.setContentType("application/json");
//        resp.getWriter().write("{\"ok\":true}");
        resp.sendRedirect("index.html");
    }

    @Override
    //删除的话也可以用浏览器验证，需要用js，比较麻烦，可以用http客户端，postman
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        //获取请求中的imageId
        String imageId = req.getParameter("imageId");
        if(imageId == null || imageId.equals("")){
            resp.setStatus(200);
            resp.getWriter().write("{\"ok\":false,\"reason\":\"解析请求失败\"}");
            return;
        }
        //通过ImageDao对象，查看图片对应的相关属性
        ImageDao imageDao = new ImageDao();
        Image image = imageDao.selectOne(Integer.parseInt(imageId));
        if(image == null){
            //请求传入的在数据库中不存在
            resp.setStatus(200);
            resp.getWriter().write("{\"ok\":false,\"reason\":\"imageId在数据库中不存在\"}");
            return;
        }
        //3.删除数据库中的记录
        imageDao.delete(Integer.parseInt(imageId));
        //4.删除本地磁盘的文件
        File file = new File(image.getPath());
        file.delete();
        resp.setStatus(200);
        resp.getWriter().write("{\"ok\":true}");
    }
}

