package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class Goods {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;

    //列出所有商品（这里不列出商品的进货价，进价部分只有管理员可见）
    // 商品的信息包括：商品编号、商品名称、生产厂家、生产日期、型号、进货价、零售价格、数量。所有信息都要相互关联//8要素
    public void showAllGoods() throws SQLException {
        System.out.println();
        System.out.println("************************以下为全部商品信息*************************");
        sql = "SELECT * FROM goods";
        resultSet = dataBase.statement.executeQuery(sql);
        System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
        while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
            int id = resultSet.getInt("goodsID");
            String name = resultSet.getString("goodsName");
            String manufacturer = resultSet.getString("goodsManufacture");
            Date date = resultSet.getDate("goodsDateOfProduction");
            String type = resultSet.getString("goodsTypes");
            double ExitPrice = resultSet.getDouble("goodsExitPrice");
            int count = resultSet.getInt("goodsCount");
            System.out.println("\t"+id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + ExitPrice + "\t\t" + count + "\t");
        }
        System.out.println("***************************************************************");
    }
        //查询商品
        //可以根据商品名称、生产厂家、零售价格进行单独查询或者组合查询
        public  void searchGoods() throws SQLException {
            System.out.println("请选择您的查询方式：");
            System.out.println("\t1--商品名或生产厂家查询\n\t2--零售价查询");
            Scanner choose = new Scanner(System.in);
            int choose6 = choose.nextInt();
            switch (choose6) {
                case 1:
                    System.out.println();
                    System.out.println("*********欢迎来到商品查询界面********");
                    System.out.println("|\t1--输入的商品名或生产厂家查询     |");
                    System.out.println("|\t2--输入的商品名和生产厂家查询     |");
                    System.out.println("*********************************");
                    System.out.println("请输入您的选择：");
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
                        case 1:
                            System.out.println("请输入您要搜索商品的商品名或生产厂家：");
                            Scanner sch = new Scanner(System.in);
                            String search = sch.next();
                            String sql = "SELECT  FORM  goods WHERE goodsName = search or goodsManufacturer = search ";
                            resultSet = dataBase.statement.executeQuery(sql);

                            if(resultSet.next()){
                                System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
                                while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                                    int id = resultSet.getInt("goodsNumber");
                                    String name = resultSet.getString("goodsName");
                                    String manufacturer = resultSet.getString("goodsManufacturer");
                                    Date date = resultSet.getDate("goodsDateOfProduction");
                                    String type = resultSet.getString("goodsTypes");
                                    double importPrice= resultSet.getDouble("goodsImportPrice");
                                    double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                    int count = resultSet.getInt("goodsCount");
                                    System.out.println("\t"+id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + importPrice + "\t" + ExitPrice + "\t" + count + "\t");
                                }
                            } else {
                                System.out.println("暂无此商品的任何信息！");
                            }
                            break;
                        case 2://有BUG不能查询出结果
                            System.out.println("请输入您要搜索商品的商品名和生产厂家：(两者之间用加号连接)");
                            Scanner sch1 = new Scanner(System.in);
                            String search1 = sch1.next();
                            //正则表达分割输入的字符串
                            String[] str = new String[2];
                            str = search1.split("\\+");
                            String str1 = str[0];
                            String str2 = str[1];
                                sql = "SELECT  FORM  goods WHERE goodsName = str1 AND goodsManufacturer = str2 ";
                                resultSet = dataBase.statement.executeQuery(sql);
                                String sql2 = "SELECT  FORM  goods WHERE goodsName = str2 or goodsManufacturer = str1 ";
                                ResultSet resultSet2 = dataBase.statement.executeQuery(sql2);
                                if(resultSet.next()||resultSet2.next()) {
                                    System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
                                    while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                                        int id = resultSet.getInt("goodsNumber");
                                        String name = resultSet.getString("goodsName");
                                        String manufacturer = resultSet.getString("goodsManufacturer");
                                        Date date = resultSet.getDate("goodsDateOfProduction");
                                        String type = resultSet.getString("goodsTypes");
                                        double importPrice = resultSet.getDouble("goodsImportPrice");
                                        double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                        int count = resultSet.getInt("goodsCount");
                                        System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + importPrice + "\t" + ExitPrice + "\t" + count + "\t");
                                    }
                                } else {
                                System.out.println("暂无此商品的任何信息！");
                            }
                            break;
                    }


                    break;
                case 2:
                    System.out.println("请输入商品零售价进行查询(价格范围，如：0-100)：");
                    Scanner prc = new Scanner(System.in);
                    String price = prc.next();
                    //正则表达分割输入的字符串
                    String[] str = new String[2];
                    str = price.split("\\-");
                    String str1 = str[0];
                    double price1 = Double.parseDouble(str1);
                    String str2 = str[1];
                    double price2 = Double.parseDouble(str2);
                    String sql = "SELECT  FORM  goods WHERE goodsExitPrice BETWEEN price1 AND price2";
                    resultSet = dataBase.statement.executeQuery(sql);
                    if(resultSet.next()){
                        System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
                        while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                            int id = resultSet.getInt("goodsNumber");
                            String name = resultSet.getString("goodsName");
                            String manufacturer = resultSet.getString("goodsManufacturer");
                            Date date = resultSet.getDate("goodsDateOfProduction");
                            String type = resultSet.getString("goodsTypes");
                            double importPrice = resultSet.getDouble("goodsImportPrice");
                            double ExitPrice = resultSet.getDouble("goodsExitPrice");
                            int count = resultSet.getInt("goodsCount");
                            System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + importPrice + "\t" + ExitPrice + "\t" + count + "\t");
                        }
                    } else {
                        System.out.println("暂无此商品的任何信息！");
                    }

            }
        }
    }
