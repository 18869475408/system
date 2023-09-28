package com.ShoppingSystem_2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
/**
 * @author 陈俊睿
 * @version 2.0
 */
@SuppressWarnings({"all"})
public class CommodityMg extends Goods {//商品管理
    //添加商品//商品的信息包括：商品编号、商品名称、生产厂家、生产日期、型号、进货价、零售价格、数量
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet;
    public void addGoods() throws ParseException, SQLException {
        TABtoCustomer chooseAS = new TABtoCustomer();
        System.err.println("您现在正在以管理员身份进行商品操作");

        System.out.print("请输入添加商品名：");
        Scanner goodsNam = new Scanner(System.in);
        String goodsNam1 = goodsNam.next();

        System.out.print("请输入添加商品的编号：");
        Scanner goodsNum = new Scanner(System.in);
        int goodsNum1 = goodsNum.nextInt();
        sql = "SELECT goodsID FROM goods WHERE goodsID = " + goodsNum1 + "";
        resultSet = dataBase.statement.executeQuery(sql);
        while (resultSet.next()) {
            sql = "SELECT goodsID FROM goods WHERE goodsID = " + goodsNum1 + "";
            resultSet = dataBase.statement.executeQuery(sql);
            System.out.print("该商品编号已有商品，请重新输入：");
            goodsNum1 = goodsNum.nextInt();
        }



        System.out.print("请输入添加商品生产厂商：");
        Scanner goodsMft = new Scanner(System.in);
        String goodsMft1 = goodsMft.next();

        System.out.print("请输入添加商品生产日期：");
        Scanner goodsDP = new Scanner(System.in);
        String goodsDP11 = goodsDP.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = dateFormat.parse(goodsDP11);
        java.sql.Date goodsDP1 = new java.sql.Date(parse.getTime());

        System.out.print("请输入添加商品型号：");
        Scanner goodsTp = new Scanner(System.in);
        String goodsTp1 = goodsTp.next();

        System.out.print("请输入添加商品进价：");
        Scanner goodsIP = new Scanner(System.in);
        Double goodsIP1 = goodsIP.nextDouble();

        System.out.print("请输入添加商品零售价：");
        Scanner goodsEP = new Scanner(System.in);
        Double goodsEP1 = goodsEP.nextDouble();

        System.out.print("请输入添加商品数量：");
        Scanner goodsCt = new Scanner(System.in);
        int goodsCt1 = goodsCt.nextInt();

        sql = "INSERT INTO goods VALUES('"+goodsNam1+"',"+goodsNum1+",'"+goodsMft1+"','"+goodsDP1+"','"+goodsTp1+"',"+goodsIP1+","+goodsEP1+","+goodsCt1+")";
        try {
           dataBase.statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("该商品已经添加完毕，是否继续");
        new TABtoAdmin().adminTrend();
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
                System.err.println("添加下一件商品：");
                addGoods();
                break;
            case 2:
                try {
                    new Admin().goodsManage();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;//后期需要找到合适的返回点
        }

    }
    //修改商品信息
    public void modifyGoodsInformation() throws SQLException, ParseException {
        System.err.println("你现在正在执行商品信息修改操作");
        System.out.print("请输入商品名进行修改：");
        Scanner gdsName = new Scanner(System.in);
        String gdName = gdsName.next();
        sql = "SELECT * FROM goods WHERE goodsName = '"+gdName+"'";
        resultSet = dataBase.statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("*****************修改的商品信息******");
            System.out.println("|\t1--商品编号");
            System.out.println("|\t2--商品名称");
            System.out.println("|\t3--生产厂家 ");
            System.out.println("|\t4--生产日期");
            System.out.println("|\t5--产品型号");
            System.out.println("|\t6--进货价");
            System.out.println("|\t7--零售价");
            System.out.println("|\t8--商品数量");
            System.out.println("***********************************");
            System.out.print("请选择：");
            Scanner scnr = new Scanner(System.in);
            int num = -1;
            String s;
            do {
                System.out.print("请输入数字1-8：");
                s = scnr.next().trim(); // trim so that numbers with whitespace are valid
                if (s.matches("\\d+")) { // if the string contains only numbers 0-9
                    num = Integer.parseInt(s);
                }
            } while(num < 1 || num > 8 ||!scnr.hasNextLine());
            switch (num) {
                case 1://修改商品编号
                    System.out.print("请输入修改后的商品编号：");
                    Scanner alt = new Scanner(System.in);
                    int alter = alt.nextInt();
                    sql = "SELECT goodsID FROM goods WHERE goodsID = " + alter + "";
                    resultSet = dataBase.statement.executeQuery(sql);
                    while (resultSet.next()) {
                        sql = "SELECT goodsID FROM goods WHERE goodsID = " + alter + "";
                        resultSet = dataBase.statement.executeQuery(sql);
                        System.out.print("该商品编号已有商品，请重新输入：");
                        alter = alt.nextInt();
                    }
                    sql = "UPDATE  goods SET goodsID = '"+alter+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 2://修改商品名称
                    System.out.print("请输入修改后的商品名称：");
                    Scanner alt1 = new Scanner(System.in);
                    String alter1 = alt1.next();
                    sql = "UPDATE goods SET goodsName = '"+alter1+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 3://修改商品生产厂家
                    System.out.print("请输入新的商品厂家：");
                    Scanner alt2 = new Scanner(System.in);
                    String alter2 = alt2.next();
                    sql = "UPDATE  goods SET goodsManufacture = '"+alter2+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 4://修改生产日期
                    System.out.print("请输入新的生产日期：");
                    Scanner goodsDP = new Scanner(System.in);
                    String goodsDP11 = goodsDP.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = dateFormat.parse(goodsDP11);
                    java.sql.Date goodsDP1 = new java.sql.Date(parse.getTime());
                    sql = "UPDATE  goods SET goodsDateOfProduction = '"+goodsDP1+"' WHERE goodsName ='"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 5://修改产品型号
                    System.out.print("请输入修改后的产品型号：");
                    Scanner alt4 = new Scanner(System.in);
                    String alter4 = alt4.next();
                    sql = "UPDATE  goods SET goodsTypes = '"+alter4+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 6://修改商品进价
                    System.out.print("请输入修改后的商品进价：");
                    Scanner alt5 = new Scanner(System.in);
                    double alter5 = alt5.nextDouble();
                    sql = "UPDATE  goods SET goodsImportPrice = '"+alter5+"' WHERE goodsName ='"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 7://修改商品零售价
                    System.out.print("请输入修改后的商品零售价：");
                    Scanner alt6 = new Scanner(System.in);
                    double alter6 = alt6.nextDouble();
                    sql = "UPDATE goods SET goodsImportPrice = '"+alter6+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
                case 8://修改商品数量
                    System.out.print("请输入修改后的商品数量：");
                    Scanner alt7 = new Scanner(System.in);
                    int alter7 = alt7.nextInt();
                    sql = "UPDATE goods SET goodsCount = '"+alter7+"' WHERE goodsName = '"+gdName+"'";
                    dataBase.statement.executeUpdate(sql);
                    break;
            }
            System.out.println("商品修改成功~~~~");
            //判断是否继续重复这个操作
            System.out.println("是否继续修改？");
            System.out.println("\t1--是\n\t2--否");
            Scanner chic = new Scanner(System.in);
            Scanner scnr1 = new Scanner(System.in);
            int num1 = -1;
            String s1;
            do {
                System.err.print("请输入数字1-2：");
                s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                    num1 = Integer.parseInt(s1);
                }
            } while(num1 < 1 || num1 > 2 ||!scnr.hasNextLine());
            switch (num1) {
                case 1:
                    modifyGoodsInformation();
                    break;
                case 2:
                    break;
            }
        } else {
            System.err.println("商品名不正确或商品不存在，请重试。");
            modifyGoodsInformation();
        }
    }
    //删除商品
    public void deleteGoodsInformation() throws SQLException, ParseException {
        System.out.println("你正在执行商品删除操作~~~");
        System.out.print("请输入商品名进行删除：");
        Scanner gdName = new Scanner(System.in);
        String gdsName = gdName.next();
        sql = "SELECT * FROM goods WHERE goodsName = '"+gdsName+"'";
        resultSet = dataBase.statement.executeQuery(sql);
            System.err.println("该操作执行后被删除的数据不可恢复，是否继续？");
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
                    if (resultSet.next()) {
                       sql = "DELETE FROM goods WHERE goodsName = '"+gdsName+"'";
                        dataBase.statement.executeUpdate(sql);
                        System.out.println("商品已成功删除！");
                        System.out.println("是否继续删除操作？");
                        System.out.println("\t1--是\n\t2--否");
                        Scanner scnr1 = new Scanner(System.in);
                        int num1 = -1;
                        String s1;
                        do {
                            System.err.print("请输入数字1-2：");
                            s1 = scnr1.next().trim(); // trim so that numbers with whitespace are valid
                            if (s1.matches("\\d+")) { // if the string contains only numbers 0-9
                                num1 = Integer.parseInt(s1);
                            }
                        } while(num1 < 1 || num1 > 2 ||!scnr.hasNextLine());
                        switch (num1) {
                            case 1:
                                deleteGoodsInformation();
                                break;
                            case 2:
                                break;
                        }

                    } else {
                        System.out.println("商品名不正确或商品不存在，请重试。");
                        new TABtoAdmin().adminTrend();
                    }
                    break;
                case 2:
                    break;
            }
        }

    //罗列商品
    public void showAllGoodsToAdmin() throws SQLException {
        Goods goods1 = new Goods();
        goods1.showAllGoods();//调用goods类的方法罗列出所有商品信息
    }
}
