package com.ShoppingSystem_3;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})

public class ShoppingMg{
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
            System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t" + date + "\t" + type + "\t" + ExitPrice + "\t\t" + count + "\t");
        }
        System.out.println("*********************************************************");
    }
    //修改购物车中的商品//修改商品数量，当数量小于或等于0，则将该商品从购物车中清除。
    public void modifyGoodsOfShopCar() throws SQLException {
        sql = "DELETE FROM shopcar WHERE ownGoodsCount = "+0+" AND customerID = "+Conf.id+"";
        dataBase.statement.executeUpdate(sql);
        }
    }
