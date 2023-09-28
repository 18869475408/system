package com.ShoppingSystem_3;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class CustomerMg {//客户管理
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;

    //罗列出所有客户信息
    //客户信息包括：客户ID、用户名、用户级别（金牌客户、银牌客户、铜牌客户）、用户注册时间、客户累计消费总金额、用户手机号、用户邮箱；
    public void showCustomer() throws SQLException {
        System.out.println("以下为全部客户信息：");
        System.out.println("客户ID\t用户名\t用户级别\t\t用户注册时间\t\t\t消费总金额\t\t\t用户邮箱\t\t\t用户手机号\t");
        sql = "SELECT * FROM customs";
        resultSet = dataBase.statement.executeQuery(sql);
        while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
            int id = resultSet.getInt("customerID");
            String name = resultSet.getString("customerName");
            String grade = resultSet.getString("customerGrade");
            String time = resultSet.getString("registrationTime");
            double cost = resultSet.getDouble("costTotal");
            String email = resultSet.getString("customerEmail");
            String phone = resultSet.getString("customerPhoneNumber");
            System.out.println("\t" + id + "\t" + name + "\t" + grade + "\t" + time + "\t" +  cost+ "\t\t\t" + email + "\t" + phone + "\t");
        }
    }
}