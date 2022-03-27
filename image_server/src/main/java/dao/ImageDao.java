//dao :数据访问对象 实现业务逻辑和数据库访问相分离，对一张表的所有操作封装在XXX对象中，不管dao返回的是整数还是结果集List<>对象，都交给dao处理
package dao;
// TODO: 2021/9/4 dao依赖数据库，一般先写dao层，如果是对象.close()可能会报空指针，所以放在try catch finally的结构中；增删改一样(exexuteupdate)，查询(excuteQuery)不同；
//todo ResultSet返回查询到的多条数据，只能用while循环，rs初始时指向第0行（和迭代器类似），next方法（首先把指针下移，再判断元素是否为空） rs.getXX获取指向行的数据
//todo:connection 产生statement prparedstatement(子接口： 增删改查询赋值操作setXXX) calllablestatement对象；RESULTSET(next().getXX获取字段值（通过字段名/下标）)
//todo:封装数据的模型 实体类对应一张表
import commom.JavaImageServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {

    public void insert(Image image){//传imageId、imageName....太过零散，封装好的类Image
        //1.获取数据库连接
        Connection c = DBUtil.getConnection();
        String sql = "insert into image_tables values(null,?,?,?,?,?,?)";//问号作为占位符参数标记需要后面动态...
        PreparedStatement statement = null;//ps是一个子接口  预编译 解决sql注入问题 动态填充数据
        //预处理语句执行批量更新，通过PreparedStatemnet接口中定义的set方法为设占位符置具体的值。每次执行这个语句时不需要重新设置，设置的值是可以保持的。
        try{
            statement = c.prepareStatement(sql);
            statement.setString(1,image.getImageName());
            statement.setInt(2,image.getSize());
            statement.setString(3,image.getUploadTime());
            statement.setString(4,image.getMd5());
            statement.setString(5,image.getContentType());
            statement.setString(6,image.getPath());

            int ret = statement.executeUpdate();//返回值表示增删改几条数据
            if(ret != 1){
                throw new JavaImageServerException("插入数据库出错！");
            }
        }catch(SQLException | JavaImageServerException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(c,statement,null);
        }
    }

    public List<Image> selectAll(){
        List<Image> images = new ArrayList<>();
        //数据库连接
        Connection c = DBUtil.getConnection();

        String sql = "select * from image_tables";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //ResultSet对象表示select语句查询到的记录集合，
        //对结果集对象每行进行处理，用它next()方法，第一次调用使当前行成为有效行，游标依次向后移动。直到返回false说明已经没有记录
        try{
            //3.执行sql语句
            statement = c.prepareStatement(sql);
            resultSet = statement.executeQuery();
            //4.处理结果集
            while(resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));//setXXX就是未占位符赋值
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setMd5(resultSet.getString("md5"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                images.add(image);
            }
            return images;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(c,statement,resultSet);
        }
        return null;
    }

    public Image selectOne(int imageId) {
        // 1. 获取数据库连接
        Connection connection = DBUtil.getConnection();
        // 2. 构造 SQL 语句
        String sql = "select * from image_tables where imageId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 3. 执行 SQL 语句
            statement = connection.prepareStatement(sql);
            statement.setInt(1, imageId);
            resultSet = statement.executeQuery();
            // 4. 处理结果集
            if (resultSet.next()) {
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));//也可以通过列的名字来获得
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭链接
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public void delete(int imageId){
        Connection c = DBUtil.getConnection();
        String sql = "delete from image_tables where imageId = ?";
        PreparedStatement statement = null;
        try{
            statement = c.prepareStatement(sql);
            statement.setInt(1,imageId);
            int ret = statement.executeUpdate();
            if(ret != 1){
                throw new JavaImageServerException("删除数据库操作失败");
            }
        }catch(SQLException | JavaImageServerException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(c,statement,null);
        }
    }

    public Image selectByMd5(String md5){
        Connection c = DBUtil.getConnection();
        String sql = "select * from image_tables where md5 = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            //3.执行SQL语句
            statement = c.prepareStatement(sql);
            statement.setString(1,md5);
            resultSet = statement.executeQuery();
            //4.处理结果集
            if(resultSet.next()){
                Image image = new Image();
                image.setImageId(resultSet.getInt("imageId"));
                image.setImageName(resultSet.getString("imageName"));
                image.setSize(resultSet.getInt("size"));
                image.setUploadTime(resultSet.getString("uploadTime"));
                image.setContentType(resultSet.getString("contentType"));
                image.setPath(resultSet.getString("path"));
                image.setMd5(resultSet.getString("md5"));
                return image;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(c,statement,resultSet);
        }
        return null;
    }

//    public static void main(String[] args) {
//        Image image = new Image();
//        image.setImageName("1.png");
//        image.setSize(100);
//        image.setUploadTime("20200808");
//        image.setContentType("image/png");
//        image.setPath("./data/1.png");
//        image.setMd5("11223344");
//        ImageDao imageDao = new ImageDao();
//        imageDao.insert(image);
//
//    }
}
