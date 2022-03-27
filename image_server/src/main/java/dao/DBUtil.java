package dao;
//todo:简化数据库操作

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//dao数据访问层，类围绕着数据操作展开
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/image_server?characterEncoding=utf8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ln547423";

    private static volatile MysqlDataSource dataSource = null;//volatile关键字

    public static DataSource getDataSource(){
        if(dataSource == null) {
            //第一次执行到的时候才需要加锁
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    dataSource.setURL(URL);
                    dataSource.setUser(USERNAME);
                    dataSource.setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection()  {
        try {
            return getDataSource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void close(Connection c, PreparedStatement ps, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(c != null){
                c.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
