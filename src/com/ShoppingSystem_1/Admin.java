package com.ShoppingSystem_1;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class Admin extends User {
        //管理员登录:admin，密码为ynuadmin
        String adminName = "admin";
        String adminPassWord = "ynuadmin";

        public void singIn() {
            System.out.print("请输入您的密码：");
            Scanner psa = new Scanner(System.in);
            String passWord = psa.next();
            while (!passWord.equals(adminPassWord)) {
                System.err.print("密码错误！请重新输入:");
                Scanner sc = new Scanner(System.in);
                passWord = sc.next();
            }
                System.out.println("登陆成功！");
        }

        //修改自身密码
        public String setSelfPassWord() {
            System.out.print("请输入原密码:");
            Scanner sc = new Scanner(System.in);
            String oldPassword = sc.next();
            if (oldPassword.equals(adminPassWord)) {
                System.out.print("密码正确，请输入新密码：");
                Scanner scanner = new Scanner(System.in);
                String newPassword1 = scanner.next();
                System.out.print("请再次输入新密码：");
                String newPassword2 = scanner.next();
                while (!newPassword1.equals(newPassword2)) {
                    System.err.print("两次密码不一致,修改失败！请重新输入新密码：");
                    newPassword1 = scanner.next();
                    System.out.print("请再次输入新密码：");
                    newPassword2 = scanner.next();
                    }
                    System.out.println("密码修改成功!");
                    }
            else {
                System.err.print("密码输入错误,修改失败！");
                setSelfPassWord();//返回上一级
            }
            return adminPassWord;
        }
        //修改用户密码
        public String changeCustomerPassword() {
            PasswordMg passwordMg = new PasswordMg();
            passwordMg.setNewPassword();//调用方法修改用户密码
            changeCustomerPassword();//返回上一级
            return changeCustomerPassword();
        }

        //客户管理
        public void customerManagement(){
            System.out.println();
            System.out.println("********欢迎来到客户管理界面**********");
            System.out.println("|\t1--罗列客户                    |");
            System.out.println("|\t2--删除客户                    |");
            System.out.println("|\t3--搜索客户                    |");
            System.out.println("*********************************");
            System.out.print("请选择你要执行的操作：");

            CustomerMg manage = new CustomerMg();
            Scanner opr = new Scanner(System.in);
            int operate = opr.nextInt();
            switch (operate) {
                case 1:
                    //罗列客户
                    manage.showCustomer();
                    break;
                case 2:
                    //删除客户
                    manage.deleteCustomer();
                    break;
                case 3:
                    //搜索客户
                    manage.searchCustomer();
                    break;
                default:
                    //输入有误
                    this.customerManagement();
                    break;
            }
        }

        //商品管理
        public void goodsManage() {
            CommodityMg commodity = new CommodityMg();
            System.out.println();
            System.out.println("********欢迎来到商品管理界面***********");
            System.out.println("|\t1--罗列商品                     |");
            System.out.println("|\t2--添加商品信息                  |");
            System.out.println("|\t3--修改商品信息                  |");
            System.out.println("|\t4--删除商品信息                  |");
            System.out.println("***********************************");
            System.out.print("请选择你要执行的操作：");
            Goods good = new Goods();
            Scanner choose = new Scanner(System.in);
            int choose1 = choose.nextInt();
            switch (choose1) {
                //罗列商品
                case 1:
                    good.addGoods0();
                    commodity.showAllGoodsToAdmin();
                    break;
                //添加商品信息
                case 2:
                    good.addGoods0();
                    commodity.addGoods();
                    break;
                //修改商品信息
                case 3:
                    good.addGoods0();
                    commodity.modifyGoodsInformation();
                    break;
                //删除商品信息
                case 4:
                    good.addGoods0();
                    commodity.deleteGoodsInformation();
                    break;

                default:
                    //输入有误
                    this.goodsManage();
                    break;
            }
        }
    }
