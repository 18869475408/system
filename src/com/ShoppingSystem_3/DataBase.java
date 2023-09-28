package com.ShoppingSystem_3;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class DataBase {
    Connection connection = null;
    Statement statement = null;
    public int connectDataBase(){
        //通过 Properties 对象获取配置文件的信息
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\mysql.properties"));
            //获取相关的值
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");

            //1.注册驱动
            Class.forName(driver);//建议写上

            //2.得到连接
          connection = DriverManager.getConnection(url, user, password);

            //3.得到Statement
          statement = connection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0 ;
    }
}


