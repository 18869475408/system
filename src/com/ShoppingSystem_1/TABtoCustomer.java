package com.ShoppingSystem_1;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class TABtoCustomer extends Customer {
    public void chooseAfterSignIn() {
        System.out.println();
        System.out.println("*********欢迎您来到用户操作界面*******");
        System.out.println("        1.--密码管理              ");
        System.out.println("        2--购物车管理             ");
        System.out.println("        3--支付                  ");
        System.out.println("        4.-查看购物历史            ");
        System.out.println("        5--返回                  ");
        System.out.println("*********************************");
        System.out.print("请选择您接下来的操作：");
        Scanner trd = new Scanner(System.in);
        int trend = trd.nextInt();
        switch (trend) {
            //密码管理
            case 1:
                super.setPassWord();
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