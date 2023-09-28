package com.ShoppingSystem_2;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class TABtoAdmin extends Admin {
    public void adminTrend() throws SQLException, ParseException {
        System.out.println();
        System.out.println("*********欢迎您来到管理员界面*********");
        System.out.println("\t1--修改管理员密码                ");
        System.out.println("\t2--修改客户密码                  ");
        System.out.println("\t3--客户管理                     ");
        System.out.println("\t4--商品管理                     ");
        System.out.println("\t5--退出登录                     ");
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
            //修改管理员密码
            case 1:
                super.setSelfPassWord();
                adminTrend();
                break;
            //修改客户密码
            case 2:
                try {
                    super.changeCustomerPassword();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                adminTrend();
                break;
            //客户管理
            case 3:
                super.customerManagement();
                adminTrend();
                break;
            //商品管理
            case 4:
                    super.goodsManage();
                adminTrend();
                break;
            case 5:
               main(null);
        }
    }
}