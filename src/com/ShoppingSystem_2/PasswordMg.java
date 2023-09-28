package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class PasswordMg extends Customer{
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;

    public String setNewPassword() throws SQLException {
        System.out.print("请输入需要修改密码用户的用户名：");
        Scanner nma = new Scanner(System.in);
        String name = nma.next();
        sql = "SELECT * FROM customs WHERE customerName = '"+ name +"'";
        resultSet = dataBase.statement.executeQuery(sql);
        while (!resultSet.next()) {
            System.err.print("用户名输入错误或用户不存在，请检查后再次输入:");
            name = nma.next();
            sql = "SELECT * FROM customs WHERE customerName = '"+ name +"'";
            resultSet = dataBase.statement.executeQuery(sql);
        }
        System.out.print("请输入修改后的密码：");
        Scanner newPw = new Scanner(System.in);
        String newPassWord = newPw.next();
        System.out.print("请再次输入密码确认：");
        Scanner newPw1 = new Scanner(System.in);
        String newPassWord1 = newPw1.next();
        while (!newPassWord.equals(newPassWord1)) {
            System.out.print("两次密码不一致，请检查后再次输入:");
            newPassWord = newPw.next();
            System.out.print("请再次输入密码确认：");
            newPassWord1 = newPw1.next();
        }
        sql = "UPDATE customs SET customerPassword = '"+ newPassWord1 +"' WHERE customerName = '"+ name +"'";
        dataBase.statement.executeUpdate(sql);
        System.out.println("密码修改成功~~~~");
        return newPassWord1;
    }
}
