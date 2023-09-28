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

public class ShoppingMg extends Customer {
    TABtoCustomer tab = new TABtoCustomer();
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;

    public void printShopCar(int customsID) throws SQLException {
        System.out.println();
        sql = "SELECT * FROM shopcar WHERE customerID = " + customsID + "";
        resultSet = dataBase.statement.executeQuery(sql);
            System.out.println("**************************您的购物车如下********************");
            System.out.println("商品编号\t商品名\t生产商\t生产日期\t\t产品型号\t零售价\t数量\t");
            while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                int id = resultSet.getInt("ownGoodsID");
                String name = resultSet.getString("ownGoodsName");
                String manufacturer = resultSet.getString("ownGoodsManufacturer");
                Date date = resultSet.getDate("ownGoodsDateOfProduction");
                String type = resultSet.getString("ownGoodsType");
                double ExitPrice = resultSet.getDouble("ownGoodsPrice");
                int count = resultSet.getInt("ownGoodsCount");
                System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t" + date + "\t" + type + "\t"  + ExitPrice + "\t\t" + count + "\t");
            }
            System.out.println("*********************************************************");
          }



    //增加商品至购物车
    public void addGoodsToShopCar(int customerID) throws SQLException {
        Goods goods = new Goods();
        goods.showAllGoods();
        this.printShopCar(customerID);
        System.out.println();
        System.out.println("*******************");
        System.out.println("\t1--商品名添加\n\t2--商品编号添加");
        System.out.println("*******************");
        //用户选择
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
        } while(num < 1 || num > 2 ||!scnr.hasNextLine());
        //用户输入
        switch (num) {
            case 1:
                System.out.print("请输入你要购买的商品名称:");
                Scanner buy = new Scanner(System.in);
                String buyThings = buy.next();
                sql = "SELECT * FROM goods WHERE goodsName = '"+buyThings+"' ";
                resultSet = dataBase.statement.executeQuery(sql);
                if (resultSet.next()) {
                    int ID = resultSet.getInt("goodsID");
                    String manufacturer = resultSet.getString("goodsManufacture");
                    Date date = resultSet.getDate("goodsDateOfProduction");
                    String type = resultSet.getString("goodsTypes");
                    double importPrice = resultSet.getDouble("goodsImportPrice");
                    double ExitPrice = resultSet.getDouble("goodsExitPrice");
                    System.out.print("您购买的商品还剩余：");
                    int restGoodsCount = resultSet.getInt("goodsCount");
                    System.out.println(restGoodsCount);
                    System.out.print("请选择需要购买商品的数量:");
                    Scanner num1 = new Scanner(System.in);
                    int number = num1.nextInt();
                    while (number > restGoodsCount) {
                        System.out.print("商品库存不足，请重新输入：");
                        number = num1.nextInt();
                    }
                    //从商品库中减去被购买商品的数量
                    int newGoodsCount = restGoodsCount-number;
                    System.out.println("商品已成功添加至购物车，快去为宝贝买单吧！");

                    String sql2 = "SELECT ownGoodsCount FROM shopcar WHERE  customerID = '"+super.id+"' AND ownGoodsName = '"+buyThings+"' ";
                    ResultSet resultSet2 = dataBase.statement.executeQuery(sql2);
                    if(resultSet2.next()){
                        int ownGoodsCount = resultSet2.getInt("ownGoodsCount");
                        sql = "UPDATE shopcar SET ownGoodsCount = "+(ownGoodsCount+number)+" WHERE ownGoodsName = '"+buyThings+"' AND customerID = '"+super.id+"'";
                        dataBase.statement.executeUpdate(sql);
                    }
                    else {
                        sql = "INSERT INTO shopcar VALUES(" + super.id + "," + ID + ",'" + buyThings + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + number+ ")";
                        dataBase.statement.executeUpdate(sql);
                    }
                    sql = "UPDATE goods SET goodsCount = " + (restGoodsCount - number) + " WHERE goodsName = '"+buyThings+"'";
                    dataBase.statement.executeUpdate(sql);
                    System.out.println("\t1--继续\n\t2--返回");
                    Scanner scnr1 = new Scanner(System.in);
                    int num2 = -1;
                    String s1;
                    do {
                        System.out.print("请输入数字1-2：");
                        s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                        if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                            num2 = Integer.parseInt(s1);
                        }
                    } while(num2 < 1 || num2 > 2 ||!scnr.hasNextLine());
                    switch (num2) {
                        case 1:
                            addGoodsToShopCar(customerID);//返回上一级
                            break;
                        case 2:
                            tab.chooseAfterSignIn();
                            break;
                    }
                } else {
                    System.out.println("暂时还未找到您想要的商品");//判断商品是否存在
                    addGoodsToShopCar(customerID);//返回上一级
                    break;
                }

            case 2:
                System.out.print("请输入你要购买的商品编号:");
                Scanner sc = new Scanner(System.in);
                int buyThingsID = sc.nextInt();
                sql = "SELECT * FROM goods WHERE goodsID = '"+buyThingsID+"' ";
                resultSet = dataBase.statement.executeQuery(sql);
                if (resultSet.next()) {
                    String name = resultSet.getString("goodsName");
                    String manufacturer = resultSet.getString("goodsManufacture");
                    Date date = resultSet.getDate("goodsDateOfProduction");
                    String type = resultSet.getString("goodsTypes");
                    double ExitPrice = resultSet.getDouble("goodsExitPrice");
                    System.out.print("您购买的商品还剩余：");
                    int restGoodsCount = resultSet.getInt("goodsCount");
                    System.out.println(restGoodsCount);
                    System.out.print("请选择需要购买商品的数量:");
                    Scanner num1 = new Scanner(System.in);
                    int number = num1.nextInt();
                    while (number > restGoodsCount) {
                        System.out.print("商品库存不足，请重新输入：");
                        number = num1.nextInt();
                    }
                    //从商品库中减去被购买商品的数量
                    int newGoodsCount = restGoodsCount-number;
                    System.out.println("商品已成功添加至购物车，快去为宝贝买单吧！");

                    String sql2 = "SELECT ownGoodsCount FROM shopcar WHERE  customerID = '"+super.id+"' AND ownGoodsID = '"+buyThingsID+"' ";
                    ResultSet resultSet2 = dataBase.statement.executeQuery(sql2);
                    if(resultSet2.next()){
                        int ownGoodsCount = resultSet2.getInt("ownGoodsCount");
                        sql = "UPDATE shopcar SET ownGoodsCount = "+(ownGoodsCount+number)+" WHERE ownGoodsID = '"+buyThingsID+"'AND customerID = '"+super.id+"'";
                        dataBase.statement.executeUpdate(sql);
                    }
                    else {
                        sql = "INSERT INTO shopcar VALUES(" + super.id + "," + buyThingsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + number+ ")";
                        dataBase.statement.executeUpdate(sql);
                    }
                    sql = "UPDATE goods SET goodsCount = " + (restGoodsCount - number) + " WHERE goodsID = '"+buyThingsID+"'";
                    dataBase.statement.executeUpdate(sql);
                    System.out.println("\t1--继续\n\t2--返回");
                    Scanner scnr1 = new Scanner(System.in);
                    int num2 = -1;
                    String s1;
                    do {
                        System.out.print("请输入数字1-2：");
                        s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                        if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                            num2 = Integer.parseInt(s1);
                        }
                    } while(num2 < 1 || num2 > 2 ||!scnr.hasNextLine());
                    switch (num2) {
                        case 1:
                            addGoodsToShopCar(customerID);//返回上一级
                            break;
                        case 2:
                            tab.chooseAfterSignIn();
                            break;
                    }
                } else {
                    System.out.println("暂时还未找到您想要的商品");//判断商品是否存在
                    addGoodsToShopCar(customerID);//返回上一级
                    break;
                }
        }
    }

    //把商品从购物车中清除
    public void deleteGoodsFromShopCar() throws SQLException {
        this.printShopCar(super.id);
            System.err.print("请确认是否继续商品删除操作:");
            System.out.println("\t1--继续\n\t2--退出");
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
                    System.out.print("请输入需要删除的商品名：");
                    Scanner del1 = new Scanner(System.in);
                    String delGoods = del1.next();
                    sql = "SELECT * FROM shopcar WHERE ownGoodsName = '" + delGoods + "' AND customerID = '" + super.id + "'";
                    resultSet = dataBase.statement.executeQuery(sql);
                    int restOwnGoodsCount = 0;
                    int restGoodsCount = 0;
                    if (resultSet.next()) {
                        restOwnGoodsCount = resultSet.getInt("ownGoodsCount");
                        sql = "SELECT * FROM goods WHERE goodsName = '" + delGoods + "' ";
                        resultSet = dataBase.statement.executeQuery(sql);
                        if(resultSet.next()){
                            restGoodsCount = resultSet.getInt("goodsCount");
                        }
                        System.out.println("\t1--清除该商品的全部数量\n\t2--选择性清除");
                        System.out.print("请选择删除方式：");
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
                            case 1:
                                sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE ownGoodsName = '" + delGoods + "' AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                                sql = "UPDATE goods SET goodsCount = " + (restGoodsCount + restOwnGoodsCount) + " WHERE goodsName = '" + delGoods + "'";
                                dataBase.statement.executeUpdate(sql);
                                deleteGoodsFromShopCar();
                                break;

                            case 2:
                                System.out.print("请输入需要删除的商品数量：");
                                Scanner deleteNumber = new Scanner(System.in);
                                int deleteCount = deleteNumber.nextInt();
                                while (deleteCount > restOwnGoodsCount || deleteCount <= 0) {
                                    System.out.print("请重新输入:");
                                    deleteCount = deleteNumber.nextInt();
                                }
                                sql = "UPDATE shopcar SET ownGoodsCount = " + (restOwnGoodsCount - deleteCount) + "  WHERE ownGoodsName = '" + delGoods + "' AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                                sql = "UPDATE goods SET goodsCount = " + (restGoodsCount + deleteCount) + " WHERE goodsName = '" + delGoods + "'";
                                dataBase.statement.executeUpdate(sql);
                                deleteGoodsFromShopCar();
                        }
                    }
            else{
                System.err.println("购物车中没有此商品！");
                System.err.println("请重新选择！");
                deleteGoodsFromShopCar();
            }
            break;
            case 2:
                tab.chooseAfterSignIn();
                break;
        }
    }

    //修改购物车中的商品//修改商品数量，当数量小于或等于0，则将该商品从购物车中清除。
    public void modifyGoodsOfShopCar() throws SQLException {
        sql = "DELETE FROM shopcar WHERE ownGoodsCount = "+0+" ";
        dataBase.statement.executeUpdate(sql);
        System.out.println("购物车清理成功~~~");
        printShopCar(super.id);
        (new TABtoCustomer()).chooseAfterSignIn();
        }
    }
