package com.ShoppingSystem_3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class ShoppingHs{
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;

    public void getShoppingHs(int customerID) throws SQLException {
        sql = "SELECT * FROM shoppinghistory  WHERE customerID = "+ customerID +"";
        resultSet = dataBase.statement.executeQuery(sql);
        if(resultSet.next()) {
            System.out.println("**************************************以下是您的购物历史**************************************");
            System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t购买数量\t\t\t购买时间");
            sql = "SELECT * FROM shoppinghistory  WHERE customerID = "+ customerID +"";
            resultSet = dataBase.statement.executeQuery(sql);
            while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                int id = resultSet.getInt("GoodsID");
                String name = resultSet.getString("GoodsName");
                String manufacturer = resultSet.getString("Manufacturer");
                Date date = resultSet.getDate("DateOfProduction");
                String type = resultSet.getString("Type");
                double ExitPrice = resultSet.getDouble("Price");
                int count = resultSet.getInt("Count");
                String time =resultSet.getString("PurchaseTime");
                System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + ExitPrice + "\t\t" + count + "\t\t\t" + time);
            }
            System.out.println("*******************************************************************************************");
        }
        else{
            System.out.println("您无购买记录~~~~~");
        }
    }
}