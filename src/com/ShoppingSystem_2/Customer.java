package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.lang.String;

/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})


public class Customer extends User {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet=null;
    public static int id ;
    public static String customerName;

    //登录
    public void cuSignIn() throws SQLException {
        System.out.print("请输入您的用户名：");
        Scanner nm = new Scanner(System.in);
        String name1 = nm.next();

        sql = "SELECT * FROM customs WHERE customerName = '"+ name1 +"'";
        resultSet = dataBase.statement.executeQuery(sql);

        if(resultSet.next() && name1.length() > 4){
         id = resultSet.getInt("customerID");
         System.out.print("请输入您的密码：");//密码连续输入错误5次就锁定账户。
        Scanner ps = new Scanner(System.in);
        String passWord1 = ps.next();

        sql = "SELECT * FROM customs WHERE customerName = '"+ name1 +"' AND customerPassword = '"+ passWord1 +"'";
        resultSet = dataBase.statement.executeQuery(sql);

        if(resultSet.next()){
            System.out.println("登录成功！");
            TABtoCustomer trend = new TABtoCustomer();
            trend.chooseAfterSignIn();
        } else {
            System.out.println();
            System.out.println("*********密码错误!********");
            System.out.println("|\t1--重新输入           |");
            System.out.println("|\t2---忘记密码          |");
            System.out.println("************************");
            System.out.print("请选择：");
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
            switch (num) {
                //重新输入 //连续输入错误5次就锁定账户
                case 1:
                    int count = 1;
                    while (true) {
                        int times = 5 - count;
                        if (count < 5) {
                            System.out.println("第" + count + "次输入的密码不正确，您还有" + times + "次机会");
                            System.out.print("请输入您的密码：");
                            Scanner ps1 = new Scanner(System.in);
                            String passWord2 = ps1.next();
                            sql = "SELECT * FROM customs WHERE customerName = '"+ name1 +"' AND customerPassword = '"+ passWord2 +"'";
                            resultSet = dataBase.statement.executeQuery(sql);
                            if (resultSet.next()) {
                                System.out.println("登录成功！");
                                TABtoCustomer trend = new TABtoCustomer();
                                trend.chooseAfterSignIn();
                            }
                            count++;
                        }
                        if (count == 5) {
                            System.err.println("您5次输入的密码都不正确，账号即将被锁定！");
                            System.exit(0);
                        }
                    }
                    //忘记密码
                case 2:
                    this.setPassWord(name1);
                    cuSignIn();
                    break;
            }
        }
    } else {
        System.err.println("用户名输入错误！请重新输入。");
        cuSignIn();//返回上一级
    }

}

    //密码管理
        public String setPassWord(String name) throws SQLException {
            System.out.print("请输入修改后的密码：");
            Scanner newPw = new Scanner(System.in);
            String newPassWord = newPw.next();
            while (!(newPassWord.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$"))){
                System.err.print("密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合，请重新输入：");
                newPassWord = newPw.next();
            }
            System.out.print("请再次输入密码确认：");
            Scanner newPw1 = new Scanner(System.in);
            String newPassWord1 = newPw1.next();
            while (!newPassWord.equals(newPassWord1)) {
                System.err.print("两次密码不一致，请检查后再次输入:");
                newPassWord = newPw.next();
                System.out.print("请再次输入密码确认：");
                newPassWord1 = newPw1.next();
            }

            sql = "UPDATE customs SET customerPassword = '"+ newPassWord1 +"' WHERE customerName = '"+ name +"'";
            dataBase.statement.executeUpdate(sql);
            System.out.println("密码修改成功,请重新登录！！！");
            this.cuSignIn();
            return newPassWord1;
        }

    //用户注册
    public void customerRegister() throws SQLException {
        System.out.print("请输入您的用户名:(用户名长度不少于5个字符)");
        Scanner nm1 = new Scanner(System.in);
        String customersName1 = nm1.next();
        sql = "SELECT * FROM customs WHERE customerName = '"+customersName1+"'";
        resultSet = dataBase.statement.executeQuery(sql);
        while (resultSet.next()||customersName1.length() < 5){
            if(customersName1.length() < 5){
            System.err.print("用户名长度不少于5个字符，请重新输入：");
            customersName1 = nm1.next();
            sql = "SELECT * FROM customs WHERE customerName = '"+customersName1+"'";
            resultSet = dataBase.statement.executeQuery(sql);
           }
            else{
            System.err.println("该名称已有小伙伴使用啦，请换一个吧~");
            customerRegister();
           }
        }

            Date signInDate = new Date();
            //Date转String

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-hh-hh");
            String str = sdf.format(signInDate);
            System.out.print(str);
            System.out.println("您于" + signInDate + "注册了账号");
            System.out.println();
            System.out.print("请设置您的登录密码:(密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合)");//密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。
            Scanner cusp1 = new Scanner(System.in);
            String cusPassword1 = cusp1.next();
            while (!(cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$"))){
                System.err.print("密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合，请重新输入：");
                cusPassword1 = cusp1.next();
            }
            //要控制后面注册用户的密码储存地址不冲突
            //用户个人信息填写
            PersonalInformation personalInformation = new PersonalInformation();
            String phone = personalInformation.bindMobilePhoneNumber();
            id = personalInformation.setCustomerID();
            String email = personalInformation.bindEmail();
            sql = "INSERT INTO customs VALUES ("+id+",'"+customersName1+"','"+cusPassword1+"',"+"'铜牌客户'"+",'"+str+"',"+0.0f+",'"+email+"', '"+phone+"')";
            dataBase.statement.executeUpdate(sql);
            System.out.println("注册成功！请登录");
            System.out.println();
            this.cuSignIn();
        }

    //购物车管理
    //可能会出现功能重复的现象
    public void shoppingMg() throws SQLException {
        ShoppingMg shoppingMg = new ShoppingMg();
        System.out.println();
        System.out.println("*********欢迎您来到购物车管理界面*******");
        System.out.println("\t1--添加商品至购物车 ");
        System.out.println("\t2--删除购物车中的商品");
        System.out.println("\t3---自动清理");
        System.out.println("***********************************");
        System.out.print("请选择对购物车的操作:");
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
        switch (num) {
                case 1:
                    shoppingMg.addGoodsToShopCar(id);
                    break;
                case 2:
                    shoppingMg.deleteGoodsFromShopCar();
                    break;
                case 3:
                    shoppingMg.modifyGoodsOfShopCar();
                    break;
        }
    }

    //支付
    public void pay() throws SQLException {
        Pay paying = new Pay();
        paying.payFor();
    }

    //购物历史
    public void shoppingHs() throws SQLException {
        ShoppingHs history = new ShoppingHs();
        history.getShoppingHs();
    }
}
