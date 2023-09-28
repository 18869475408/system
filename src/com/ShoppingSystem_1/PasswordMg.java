package com.ShoppingSystem_1;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class PasswordMg extends Customer {
    public String setNewPassword() {
        super.addCustomer0();
        System.out.print("请输入需要修改密码用户的用户名：");
        Scanner nma = new Scanner(System.in);
        String nameS = nma.next();
        int address = customerName.indexOf(nameS);//定位用户地址
        while (!customerName.contains(nameS)) {
            System.err.print("用户名输入错误或用户不存在，请检查后再次输入:");
            nameS = nma.next();
        }
        System.out.print("请输入修改后的密码：");
        Scanner newPw = new Scanner(System.in);
        String newPassWord = newPw.next();
        System.out.print("请再次输入密码确认：");
        Scanner newPw1 = new Scanner(System.in);
        String newPassWord1 = newPw1.next();
        while (!newPassWord.equals(newPassWord1)) {
            System.err.println("两次密码不一致，请检查后再次输入:");
            newPassWord = newPw.next();
            System.out.print("请再次输入密码确认：");
            newPassWord1 = newPw1.next();
        }
        cusPassword.set(address, newPassWord);
        return setNewPassword();
    }
}
