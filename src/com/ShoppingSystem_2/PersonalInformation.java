package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})

public class PersonalInformation extends Customer {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;
    //使用集合记录客户个人信息
    //客户ID、用户名、用户级别（金牌客户、银牌客户、铜牌客户）、用户注册时间、客户累计消费总金额、用户手机号、用户邮箱；


    //设置用户
    public int setCustomerID() {
        return (int)(Math.random()*96+4);
    }

    //绑定手机号
    public String bindMobilePhoneNumber() throws SQLException {
        System.out.print("请输入您的手机号进行绑定：");
        Scanner phn = new Scanner(System.in);
        String phoneNumber = phn.next();
        while(!phoneNumber.matches("1[3-9]\\d{9}")) {
            System.err.print("该手机号不是一个有效的手机号！请换个手机号重试： ");
            phoneNumber = phn.next();
        }
        sql = "SELECT customerPhoneNumber FROM customs WHERE customerPhoneNumber = '"+phoneNumber+"'";
        resultSet = dataBase.statement.executeQuery(sql);
        if (resultSet.next()) {
            System.err.println("该号码已经被注册！请换个号码重试");
            bindMobilePhoneNumber();
        } else {
            System.out.println("号码绑定成功!");
        }
        return phoneNumber;
    }

    //绑定邮箱
    public String bindEmail() throws SQLException {
        System.out.print("请输入您的邮箱账号进行绑定：");
        Scanner phn = new Scanner(System.in);
        String emailInput = phn.next();
        while(!emailInput.matches("\\w{1,30}@[a-zA-Z0-9]{2,20}(\\.[a-zA-Z0-9]{2,20}){1,2}")) {
            System.err.println("该邮箱账号不是一个有效的邮箱号！请换个邮箱账号重试： ");
            emailInput = phn.next();
        }
        sql = "SELECT customerEmail FROM customs WHERE customerEmail = '"+emailInput+"'";
        resultSet = dataBase.statement.executeQuery(sql);
        if (resultSet.next()) {
            System.err.println("该邮箱账号已经被注册！请换个邮箱账号重试");
            bindEmail();
        } else {
            System.out.println("邮箱账号绑定成功!");
        }
        return emailInput;
    }



    //客户级别
    public void grade() throws SQLException {
        sql = "SELECT FROM customs WHERE costTotal BETWEEN 0.0 AND 1000.0";
        resultSet = dataBase.statement.executeQuery(sql);
        sql = "UPDATE customs SET customerGrade = '铜牌客户' ";
        dataBase.statement.executeUpdate(sql);

        sql = "SELECT FROM customs WHERE costTotal BETWEEN 1000.0 AND 5000.0";
        resultSet = dataBase.statement.executeQuery(sql);
        sql = "UPDATE customs SET customerGrade = '银牌客户' ";
       dataBase.statement.executeUpdate(sql);

        sql = "SELECT FROM customs WHERE costTotal > 5000.0 ";
        resultSet = dataBase.statement.executeQuery(sql);
        sql = "UPDATE customs SET customerGrade = '铜牌客户' ";
        dataBase.statement.executeUpdate(sql);
    }
}