package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class CustomerMg extends Customer {//客户管理
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

    //删除客户
    public void deleteCustomer() throws SQLException, ParseException {
        System.out.print("请输入用户名进行删除操作：");
        Scanner input = new Scanner(System.in);
        String input1 = input.next();
        sql = "SELECT * FROM customs WHERE customerName = '"+input1+"'";
        resultSet = dataBase.statement.executeQuery(sql);
        if(resultSet.next())
        {
            System.out.println("你现在正在执行客户删除操作，是否继续？");
            System.out.println("\t1--是\n\t2--否");
            Scanner scnr = new Scanner(System.in);
            int num = -1;
            String s;
            do {
                System.out.print("请输入数字1-2：");
                s = scnr.next().trim(); // trim so that numbers with whitespace are valid
                if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                    num = Integer.parseInt(s);
                }
            } while(num < 1 || num > 3 ||!scnr.hasNextLine());
            switch(num){
                case 1:
                    sql = "DELETE FROM customs WHERE customerName = '"+input1+"'";
                    dataBase.statement.executeUpdate(sql);
                    System.out.println("客户删除成功~~~");
                case 2:
                   new TABtoAdmin().adminTrend();
                    break;
            }
        }else{
            System.err.println("查询不到该用户的相关信息!");
        }
    }

    //搜索客户
    //可以根据客户ID或者客户的用户名进行查询，也可以一次查询所有客户的信息。
    public void searchCustomer() throws SQLException, ParseException {
        System.out.println();
        System.out.println("*********欢迎管理员来到搜索客户界面****");
        System.out.println("\t1--通过客户ID进行查询            ");
        System.out.println("\t2--通过客户的用户名进行查询        ");
        System.out.println("*********************************");
        System.out.print("请选择查询方式：");
        Scanner scnr = new Scanner(System.in);
        int num = -1;
        String s;
        do {
            System.out.print("请输入数字1-2：");
            s = scnr.next().trim(); // trim so that numbers with whitespace are valid
            if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                num = Integer.parseInt(s);
            }
        } while(num < 1 || num > 3 ||!scnr.hasNextLine());
        switch(num){
            case 1: //根据客户ID进行查询
                System.out.print("请输入客户ID：");
                Scanner inputs = new Scanner(System.in);
                int inputs1 = inputs.nextInt();
                sql = "SELECT * FROM customs WHERE customerID = "+ inputs1 +"";
                resultSet = dataBase.statement.executeQuery(sql);
                if(resultSet.next()) {
                    sql = "SELECT * FROM customs WHERE customerID = "+ inputs1 +"";
                    resultSet = dataBase.statement.executeQuery(sql);
                    System.out.println("客户ID\t用户名\t用户级别\t\t用户注册时间\t\t\t消费总金额\t\t\t用户邮箱\t\t\t用户手机号\t");
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
                    else {
                    System.err.println("用户ID错误或用户不存在！");
                    new TABtoAdmin().adminTrend();
                }
                break;
            case 2://根据用户名进行查询
                System.out.print("请输入客户的用户名：");
                Scanner inputs3 = new Scanner(System.in);
                String inputs2 = inputs3.next();
                sql = "SELECT customerName FROM customs WHERE customerName = '"+inputs2+"'";
                resultSet = dataBase.statement.executeQuery(sql);
            if(resultSet.next()){
                sql = "SELECT * FROM customs WHERE customerName = '"+inputs2+"'";
                resultSet = dataBase.statement.executeQuery(sql);
                System.out.println("客户ID\t用户名\t用户级别\t\t用户注册时间\t\t\t消费总金额\t\t\t用户邮箱\t\t\t用户手机号\t");
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
                }else {
                    System.err.println("用户名输入错误或用户不存在！");
                    new TABtoAdmin().adminTrend();
                }
                break;
        }
    }
}