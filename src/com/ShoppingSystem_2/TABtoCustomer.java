package com.ShoppingSystem_2;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class TABtoCustomer extends Customer {
    public void chooseAfterSignIn() throws SQLException {
        System.out.println();
        System.out.println("*********欢迎您来到用户操作界面*******");
        System.out.println("        1.--密码管理              ");
        System.out.println("        2--购物车管理             ");
        System.out.println("        3--支付                  ");
        System.out.println("        4.-查看购物历史            ");
        System.out.println("        5--退出登录                  ");
        System.out.println("*********************************");
        System.out.print("请选择您接下来的操作：");
        Scanner scnr = new Scanner(System.in);
        int num = -1;
        String s;
        do {
            System.out.print("请输入数字1-5：");
            s = scnr.next().trim(); // trim so that numbers with whitespace are valid
            if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                num = Integer.parseInt(s);
            }
        } while(num < 1 || num > 5 ||!scnr.hasNextLine());
        switch (num) {
            //密码管理
            case 1:
                super.setPassWord(super.customerName);
                break;
            //购物车管理
            case 2:
                super.shoppingMg();
                break;
            //支付
            case 3:
                super.pay();
                break;
            //购物历史
            case 4:
                super.shoppingHs();
                break;
            //返回
            case 5:
                main(null);

        }
    }
}