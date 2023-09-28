package com.ShoppingSystem_3;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * @author 陈俊睿
 * @version 3.0
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
            System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + ExitPrice + "\t\t" + count + "\t");
        }
        System.out.println("***************************************************************");
    }


    public void showAllGoodsToAdmin() throws SQLException {
        System.out.println();
        System.out.println("************************以下为全部商品信息*************************");
        sql = "SELECT * FROM goods";
        resultSet = dataBase.statement.executeQuery(sql);
        System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t进价\t零售价\t库存\t");
        while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
            int id = resultSet.getInt("goodsID");
            String name = resultSet.getString("goodsName");
            String manufacturer = resultSet.getString("goodsManufacture");
            Date date = resultSet.getDate("goodsDateOfProduction");
            String type = resultSet.getString("goodsTypes");
            double importPrice = resultSet.getDouble("goodsImportPrice");
            double ExitPrice = resultSet.getDouble("goodsExitPrice");
            int count = resultSet.getInt("goodsCount");
            System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t"  + importPrice + "\t" + ExitPrice + "\t\t" + count + "\t");
        }
        System.out.println("***************************************************************");
    }
}