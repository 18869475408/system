package com.ShoppingSystem_1;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class Customer extends User {
    ArrayList<String> customerName = new ArrayList<>();//用户名
    ArrayList<String> cusPassword = new ArrayList<>();//客户密码
    ArrayList<String> customerSignInTime = new ArrayList<>();//用户注册时间
    String name1;

    //初始化一个客户
    public void addCustomer0() {
        customerName.add("ynu");//初始化一个用户
        cusPassword.add("Ynu123456");//用户的密码
        PersonalInformation person = new PersonalInformation();
    }

    //登录
    public void cuSignIn() {
        this.addCustomer0();
        System.out.print("请输入您的用户名：");
        Scanner nm = new Scanner(System.in);
        name1 = nm.next();
        if (customerName.contains(name1)) {
            System.out.print("请输入您的密码：");//密码连续输入错误5次就锁定账户。
            Scanner ps = new Scanner(System.in);
            String passWord1 = ps.next();
            if (cusPassword.contains(passWord1)) {
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
                Scanner ipt = new Scanner(System.in);
                int input = ipt.nextInt();
                switch (input) {
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
                                if (cusPassword.contains(passWord2)) {
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
                        this.setPassWord();
                        break;
                }
            }
        } else {
            System.err.println("用户名输入错误！请重新输入。");
            cuSignIn();//返回上一级
        }
    }

    //密码管理
    public String setPassWord() {
        PasswordMg passwordMg = new PasswordMg();
        passwordMg.setNewPassword();
        return setPassWord();
    }

    //用户注册
    public void customerRegister() {
        this.addCustomer0();
        System.out.print("请输入您的用户名:(用户名长度不少于5个字符)");
        Scanner nm1 = new Scanner(System.in);
        String customersName = nm1.next();
        //要控制后面注册用户名储存的不与之冲突
        while (!(customersName.length() > 4)){
            System.err.print("用户名长度不少于5个字符，请重新输入：");
            customersName = nm1.next();
        }
        if (customerName.contains(customersName)) {
            System.err.println("该名称已有小伙伴使用啦，请换一个吧。");
            customerRegister();
        }
        else {
            Date signInDate = new Date();
            customerSignInTime.add("2021-07-20-10-10-10");//初始化用户的注册时间，用于测试与占据数组的0号地址
            //Date转String
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-hh-hh");
            String str = sdf.format(signInDate);
            System.out.print(str);
            customerSignInTime.add(str);//将用户注册时间转换为字符串存入数组

            System.out.println("您于" + signInDate + "注册了账号");
            customerName.add(customersName);
            System.out.println();
            System.out.print("请设置您的登录密码:(密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合)");//密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合。
            Scanner cusp1 = new Scanner(System.in);
            String cusPassword1 = cusp1.next();
            while (!(cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$"))){
                System.err.print("密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合，请重新输入：");
                cusPassword1 = cusp1.next();
            }
            //要控制后面注册用户的密码储存地址不冲突
            cusPassword.add(cusPassword1);
            //用户个人信息填写
            PersonalInformation personalInformation = new PersonalInformation();
            System.out.println();
            personalInformation.bindMobilePhoneNumber();
            personalInformation.setCustomerID();
            System.out.println();
            personalInformation.bindEmail();
            System.out.println("注册成功！请登录");
            System.out.println();
            this.cuSignIn();
        }
    }

    //购物车管理
    //可能会出现功能重复的现象
    public void shoppingMg() {
        ShoppingMg shoppingMg = new ShoppingMg();
        System.out.println();
        System.out.println("*********欢迎您来到购物车管理界面*******");
        System.out.println("\t1--添加商品至购物车 ");
        System.out.println("\t2--删除购物车中的商品");
        System.out.println("\t3---自动清理");
        System.out.println("***********************************");
        System.out.print("请选择对购物车的操作:");
        Scanner chs = new Scanner(System.in);
        int choose = chs.nextInt();
        while (choose < 1 || choose > 3) {
            System.err.print("请输入数字1-3：");
            choose = chs.nextInt();
            }
        switch (choose) {
                case 1:
                    shoppingMg.addGoodsToShopCar();
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
    public void pay() {
        Pay paying = new Pay();
        paying.payFor();
    }

    //购物历史
    public void shoppingHs() {
        ShoppingHs history = new ShoppingHs();
        history.getShoppingHs();
    }

    //读取客户核心资料
    //用户名，用户密码，用户注册时间
    public void readCustomerKey() {
        try {
            BufferedReader inf = new BufferedReader(new FileReader("src\\用户名.txt"));
            BufferedReader inf1 = new BufferedReader(new FileReader("src\\用户密码.txt"));
            BufferedReader inf2 = new BufferedReader(new FileReader("src\\用户注册时间.txt"));

            String str1;
            while ((str1 = inf.readLine()) != null) {
                customerName.add(str1);
            }
            while ((str1 = inf1.readLine()) != null) {
                cusPassword.add(str1);
            }
            while ((str1 = inf2.readLine()) != null) {
                customerSignInTime.add(str1);
            }
            inf.close();
            inf1.close();
            inf2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //把用户核心资料写到文本文件中
    //包括用户名，用户密码，用户注册时间
    public void writeCustomerKey() {
        try {
            //当前为重写模式
            BufferedWriter name = new BufferedWriter(new FileWriter("src\\用户名.txt"));
            BufferedWriter password = new BufferedWriter(new FileWriter("src\\用户密码.txt"));
            BufferedWriter registerTime = new BufferedWriter(new FileWriter("src\\用户注册时间.txt"));

            for (String i : customerName) {
                name.write(i);
                name.newLine();
                name.flush();
            }
            for (String i : cusPassword) {
                password.write(i);
                password.newLine();
                password.flush();
            }
            for (String i : customerSignInTime) {
                registerTime.write(i);
                registerTime.newLine();
                registerTime.flush();
            }
            name.close();
            password.close();
            registerTime.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
