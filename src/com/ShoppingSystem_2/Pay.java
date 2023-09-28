package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class Pay extends Customer {
    Customer customer = new Customer();
    ShoppingMg shoppingMg = new ShoppingMg();
    TABtoCustomer next = new TABtoCustomer();
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;
    public int customerID;

    //支付
    public void payFor() throws SQLException {
        ShoppingMg shopMg = new ShoppingMg();
        shopMg.printShopCar(super.id);
        System.out.print("请输入您想购买的产品编号：");
        Scanner shp = new Scanner(System.in);
        int shoppingID = shp.nextInt();
        sql = "SELECT * FROM shopcar WHERE ownGoodsID =" + shoppingID + " AND  customerID = '" + super.id + "'";
        resultSet = dataBase.statement.executeQuery(sql);
        while (!resultSet.next()) {
            System.out.println("从您的购物车中找不到该商品！");
            payFor();
        }

        System.out.print("请输入您想购买的产品数量：");
        Scanner sc = new Scanner(System.in);
        int shopCount = sc.nextInt();

        sql = "SELECT ownGoodsCount FROM shopcar WHERE ownGoodsID = " + shoppingID + "  AND  customerID = '" + super.id + "'";
        resultSet = dataBase.statement.executeQuery(sql);
        resultSet.next();
        int ShopCarGoodsCount = resultSet.getInt("ownGoodsCount");

        sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
        resultSet = dataBase.statement.executeQuery(sql);
        resultSet.next();
        int GoodsCount = resultSet.getInt("goodsCount");
        double price = resultSet.getDouble("goodsExitPrice");
        while (ShopCarGoodsCount + GoodsCount < shopCount) {
            System.out.print("商品库存不足，请重新输入：");
            shopCount = sc.nextInt();
        }
        System.out.println("\t1--微信");
        System.out.println("\t2--支付宝");
        System.out.println("\t3--银行卡");
        System.out.print("请选择支付方式：");
        Scanner scnr = new Scanner(System.in);
        int num = -1;
        String s;
        do {
            System.out.print("请输入数字1-3：");
            s = scnr.next().trim(); // trim so that numbers with whitespace are valid
            if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                num = Integer.parseInt(s);
            }
        } while(num < 1 || num > 3 ||!scnr.hasNextLine());
        double total = shopCount * price;//总价
        System.out.print("请输入密码进行确认:");
        Scanner confirm = new Scanner(System.in);
        String local = confirm.next();
        sql = "SELECT * FROM customs WHERE customerID = " + super.id + " AND customerPassword = '" + local + "'";
        resultSet = dataBase.statement.executeQuery(sql);
        if (resultSet.next()) {
            switch (num) {
                case 1://微信支付
                    System.out.println("是否确认支付？\n\t1--是\t2--否");
                    Scanner scnr1 = new Scanner(System.in);
                    int num1 = -1;
                    String s1;
                    do {
                        System.out.print("请输入数字1-2：");
                        s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                        if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                            num1 = Integer.parseInt(s1);
                        }
                    } while(num < 1 || num > 2 ||!scnr.hasNextLine());
                    switch (num1) {
                        case 1:
                            System.out.println("购买成功！");
                            if (ShopCarGoodsCount < shopCount) {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                                sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                dataBase.statement.executeUpdate(sql);
                            } else {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                            }
                            sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            double costTotal = resultSet.getDouble("costTotal");
                            sql = "UPDATE customs SET costTotal = " + (costTotal + total) + " WHERE customerID = " + super.id + "";
                            dataBase.statement.executeUpdate(sql);
                            sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            int goodsID = resultSet.getInt("goodsID");
                            String name = resultSet.getString("goodsName");
                            String manufacturer = resultSet.getString("goodsManufacture");
                            Date date = resultSet.getDate("goodsDateOfProduction");
                            String type = resultSet.getString("goodsTypes");
                            double importPrice = resultSet.getDouble("goodsImportPrice");
                            double ExitPrice = resultSet.getDouble("goodsExitPrice");
                            LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                            sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                            dataBase.statement.executeUpdate(sql);
                            next.chooseAfterSignIn();
                            break;
                        case 2: {
                            next.chooseAfterSignIn();
                        }
                    }

                case 2://支付宝支付
                    System.out.println("是否确认支付？\n\t1--是\t2--否");
                    Scanner scnr2 = new Scanner(System.in);
                    int num2 = -1;
                    String s2=null;
                    do {
                        System.out.print("请输入数字1-2：");
                        s2 = scnr2.next().trim(); // trim so that numbers with whitespace are valid
                        if (s2.matches("\\d+")) { // if the string contains only numbers 0-9
                            num2 = Integer.parseInt(s2);
                        }
                    } while(num2 < 1 || num2 > 2 ||!scnr2.hasNextLine());
                    switch (num2) {
                        case 1:
                            System.out.println("购买成功！");
                            if (ShopCarGoodsCount < shopCount) {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                                sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                dataBase.statement.executeUpdate(sql);
                            } else {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                            }
                            sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            double costTotal = resultSet.getDouble("costTotal");
                            sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " + super.id + "";
                            dataBase.statement.executeUpdate(sql);
                            sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            int goodsID = resultSet.getInt("goodsID");
                            String name = resultSet.getString("goodsName");
                            String manufacturer = resultSet.getString("goodsManufacture");
                            Date date = resultSet.getDate("goodsDateOfProduction");
                            String type = resultSet.getString("goodsTypes");
                            double importPrice = resultSet.getDouble("goodsImportPrice");
                            double ExitPrice = resultSet.getDouble("goodsExitPrice");
                            LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                            sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                            dataBase.statement.executeUpdate(sql);
                            next.chooseAfterSignIn();
                            break;
                        case 2: {
                            next.chooseAfterSignIn();
                        }
                    }

                case 3://银行卡支付
                    Scanner scnr3 = new Scanner(System.in);
                    int num3 = -1;
                    String s3 = null;
                    do {
                        System.out.print("请输入数字1-2：");
                        s3 = scnr3.next().trim(); // trim so that numbers with whitespace are valid
                        if (s3.matches("\\d+")) { // if the string contains only numbers 0-9
                            num3 = Integer.parseInt(s3);
                        }
                    } while(num3 < 1 || num3 > 2 ||!scnr3.hasNextLine());
                    switch (num3) {
                        case 1:
                            System.out.println("购买成功！");
                            if (ShopCarGoodsCount < shopCount) {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                                sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                dataBase.statement.executeUpdate(sql);
                            } else {
                                sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                dataBase.statement.executeUpdate(sql);
                            }
                            sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            double costTotal = resultSet.getDouble("costTotal");
                            sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " + super.id + "";
                            dataBase.statement.executeUpdate(sql);
                            sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            resultSet.next();
                            int goodsID = resultSet.getInt("goodsID");
                            String name = resultSet.getString("goodsName");
                            String manufacturer = resultSet.getString("goodsManufacture");
                            Date date = resultSet.getDate("goodsDateOfProduction");
                            String type = resultSet.getString("goodsTypes");
                            double importPrice = resultSet.getDouble("goodsImportPrice");
                            double ExitPrice = resultSet.getDouble("goodsExitPrice");
                            LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                            sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                            dataBase.statement.executeUpdate(sql);
                            next.chooseAfterSignIn();
                            break;
                        case 2: {
                            next.chooseAfterSignIn();
                        }
                    }
            }
        } else {
            int count = 1;
            while (true) {
                int times = 5 - count;
                if (count < 5) {
                    System.out.println("第" + count + "次输入的密码不正确，您还有" + times + "次机会");
                    System.out.print("请输入您的密码：");
                    Scanner ps1 = new Scanner(System.in);
                    String passWord2 = ps1.next();
                    sql = "SELECT * FROM customs WHERE customerID = " + super.id + " AND customerPassword = '" + local + "'";
                    resultSet = dataBase.statement.executeQuery(sql);
                    if (resultSet.next()) {
                        switch (num) {
                            case 1://微信支付
                                System.out.println("是否确认支付？\n\t1--是\t2--否");
                                Scanner scnr1 = new Scanner(System.in);
                                int num1 = -1;
                                String s1;
                                do {
                                    System.out.print("请输入数字1-2：");
                                    s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                                    if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                                        num1 = Integer.parseInt(s1);
                                    }
                                } while(num < 1 || num > 2 ||!scnr.hasNextLine());
                                switch (num1) {
                                    case 1:
                                        System.out.println("购买成功！");
                                        if (ShopCarGoodsCount < shopCount) {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                            sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                            dataBase.statement.executeUpdate(sql);
                                        } else {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                        }
                                        sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        double costTotal = resultSet.getDouble("costTotal");
                                        sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " + super.id + "";
                                        dataBase.statement.executeUpdate(sql);
                                        sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        int goodsID = resultSet.getInt("goodsID");
                                        String name = resultSet.getString("goodsName");
                                        String manufacturer = resultSet.getString("goodsManufacture");
                                        Date date = resultSet.getDate("goodsDateOfProduction");
                                        String type = resultSet.getString("goodsTypes");
                                        double importPrice = resultSet.getDouble("goodsImportPrice");
                                        double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                                        sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                                        dataBase.statement.executeUpdate(sql);
                                        next.chooseAfterSignIn();
                                        break;
                                    case 2: {
                                        next.chooseAfterSignIn();
                                    }
                                }

                            case 2://支付宝支付
                                System.out.println("是否确认支付？\n\t1--是\t2--否");
                                Scanner scnr2 = new Scanner(System.in);
                                int num2 = -1;
                                String s2=null;
                                do {
                                    System.out.print("请输入数字1-2：");
                                    s1 = scnr2.next().trim(); // trim so that numbers with whitespace are valid
                                    if (s2.matches("\\d+")) { // if the string contains only numbers 0-9
                                        num2 = Integer.parseInt(s2);
                                    }
                                } while(num2 < 1 || num2 > 2 ||!scnr2.hasNextLine());
                                switch (num2) {
                                    case 1:
                                        System.out.println("购买成功！");
                                        if (ShopCarGoodsCount < shopCount) {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                            sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                            dataBase.statement.executeUpdate(sql);
                                        } else {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                        }
                                        sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        double costTotal = resultSet.getDouble("costTotal");
                                        sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " + super.id + "";
                                        dataBase.statement.executeUpdate(sql);
                                        sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        int goodsID = resultSet.getInt("goodsID");
                                        String name = resultSet.getString("goodsName");
                                        String manufacturer = resultSet.getString("goodsManufacture");
                                        Date date = resultSet.getDate("goodsDateOfProduction");
                                        String type = resultSet.getString("goodsTypes");
                                        double importPrice = resultSet.getDouble("goodsImportPrice");
                                        double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                                        sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                                        dataBase.statement.executeUpdate(sql);
                                        next.chooseAfterSignIn();
                                        break;
                                    case 2: {
                                        next.chooseAfterSignIn();
                                    }
                                }

                            case 3://银行卡支付
                                Scanner scnr3 = new Scanner(System.in);
                                int num3 = -1;
                                String s3=null;
                                do {
                                    System.out.print("请输入数字1-2：");
                                    s3 = scnr3.next().trim(); // trim so that numbers with whitespace are valid
                                    if (s3.matches("\\d+")) { // if the string contains only numbers 0-9
                                        num3 = Integer.parseInt(s3);
                                    }
                                } while(num3 < 1 || num3 > 2 ||!scnr3.hasNextLine());
                                switch (num3) {
                                    case 1:
                                        System.out.println("购买成功！");
                                        if (ShopCarGoodsCount < shopCount) {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                            sql = "UPDATE goods SET goodsCount = " + (GoodsCount - ShopCarGoodsCount + shopCount) + " WHERE goodsID = " + shoppingID + "";
                                            dataBase.statement.executeUpdate(sql);
                                        } else {
                                            sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = '" + super.id + "'";
                                            dataBase.statement.executeUpdate(sql);
                                        }
                                        sql = "SELECT costTotal FROM customs WHERE customerID = " + super.id + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        double costTotal = resultSet.getDouble("costTotal");
                                        sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " + super.id + "";
                                        dataBase.statement.executeUpdate(sql);
                                        sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
                                        resultSet = dataBase.statement.executeQuery(sql);
                                        resultSet.next();
                                        int goodsID = resultSet.getInt("goodsID");
                                        String name = resultSet.getString("goodsName");
                                        String manufacturer = resultSet.getString("goodsManufacture");
                                        Date date = resultSet.getDate("goodsDateOfProduction");
                                        String type = resultSet.getString("goodsTypes");
                                        double importPrice = resultSet.getDouble("goodsImportPrice");
                                        double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                                        sql = "INSERT INTO shoppinghistory VALUES(" + super.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                                        dataBase.statement.executeUpdate(sql);
                                        next.chooseAfterSignIn();
                                        break;
                                    case 2: {
                                        next.chooseAfterSignIn();
                                    }
                                }
                        }
                    }
                    count++;
                }
                if (count == 5) {
                    System.err.println("您5次输入的密码都不正确，即将返回用户操作界面！");
                    next.chooseAfterSignIn();
                }
            }
        }
        new PersonalInformation().grade();
    }
}



