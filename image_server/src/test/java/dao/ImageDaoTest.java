package dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageDaoTest {

    @Test
    public void insert() {
        //测试点：mariadb数据库和本地文件插入
        //路径覆盖
        Image image = new Image();
        image.setMd5("11223344");
        image.setPath("C:\\Users\\yiqieyouweifa\\Pictures");
        image.setContentType("image/jpeg");
        image.setUploadTime("20200916");
        image.setSize(7254);
        image.setImageName("ceshi.jfif");

        ImageDao imageDao = new ImageDao();
        imageDao.insert(image);

    }

    @Test
    public void selectAll() {
        ImageDao imageDao = new ImageDao();
        imageDao.selectAll();

    }

    @Test
    public void selectOne() {
        ImageDao imageDao = new ImageDao();
        imageDao.selectOne(22);
    }

    @Test
    public void delete() {
    }

    @Test
    public void selectByMd5() {
    }
}