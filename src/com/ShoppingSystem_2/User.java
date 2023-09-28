package com.ShoppingSystem_2;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 2.0
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

                Admin admin = new Admin();
                TABtoAdmin TtoAdmin = new TABtoAdmin();
                Customer customer = new Customer();

                Scanner scnr = new Scanner(System.in);
                int num = -1;
                String s;
                do {
                    System.out.print("请输入数字1-2：");
                    s = scnr.next().trim(); // trim so that numbers with whitespace are valid
                    if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                        num = Integer.parseInt(s);
                    }
                } while(num < 1 || num > 2 ||!scnr.hasNextLine());
                    switch (num) {
                        //客户
                        case 1:
                            System.out.println();
                            System.out.println("*********欢迎您来到XX购物系统********");
                            System.out.println("|           1--登录               |");
                            System.out.println("|           2--注册               |");
                            System.out.println("*********************************");
                            System.out.print("若已有账号，请登录！若没有账号，请先注册！请选择:");

                            Scanner scnr1 = new Scanner(System.in);
                            int num1 = -1;
                            String s1;
                            do {
                                System.out.print("请输入数字1-2：");
                                s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                                if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                                    num1 = Integer.parseInt(s1);
                                }
                            } while(num1 < 1 || num1 > 2 ||!scnr1.hasNextLine());
                        switch (num1) {
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