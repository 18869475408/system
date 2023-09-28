package com.ShoppingSystem_1;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 1.0
 *
 */
@SuppressWarnings({"all"})
    public class User {
        public static void main(String[] args) {
            try{
                System.out.println("*********欢迎来到XX购物系统*********");
                System.out.println("\t1--客户(customer)            ");
                System.out.println("\t2--管理员(administrator)     ");
                System.out.println("*********************************");
                System.out.print("请选择您的登录身份:");
                Scanner scanner = new Scanner(System.in);
                int choose = scanner.nextInt();

                Admin admin = new Admin();
                TABtoAdmin TtoAdmin = new TABtoAdmin();
                Customer customer = new Customer();
                PersonalInformation personalInformation = new PersonalInformation();
                //文本文件导入
                customer.readCustomerKey();
                personalInformation.acquireCustomerDate();

                while (choose < 1 || choose > 2) {
                    System.err.print("请输入数字1-2：");
                    choose = scanner.nextInt();
                }
                    switch (choose) {
                        //客户
                        case 1:
                            System.out.println();
                            System.out.println("*********欢迎您来到XX购物系统********");
                            System.out.println("|           1--登录               |");
                            System.out.println("|           2--注册               |");
                            System.out.println("*********************************");
                            System.out.print("若已有账号，请登录！若没有账号，请先注册！请选择:");
                            Scanner read = new Scanner(System.in);
                            int reader = read.nextInt();

                        switch (reader) {
                                //登录
                                case 1:
                                    customer.cuSignIn();
                                    break;
                                //注册
                                case 2:
                                    //调用customer的方法
                                    customer.customerRegister();
                                    break;
                            }
                        //管理员登录
                        case 2:
                            admin.singIn();
                            TtoAdmin.adminTrend();
                            }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }