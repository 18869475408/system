package com.ShoppingSystem_3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
/**
 /**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class PayConfirm extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int ShopCarGoodsCount;
    int shopCount;
    int shoppingID;
    double total;
    private static  int count = 0;
    public static int times = 5 - count;
    private JLabel lbPass = new JLabel(" 请输入密码进行确认：");
    private JPasswordField PayPass  = new JPasswordField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  PayConfirm(int ShopCarGoodsCount,int shopCount,int shoppingID,double total){
        super("支付密码~~~~");
        this.ShopCarGoodsCount =ShopCarGoodsCount;
        this.shopCount = shopCount;
        this.shoppingID = shoppingID;
        this.total = total;
        this.setLayout(new FlowLayout());
        this.add(lbPass);
        this.add(PayPass);
        this.add(But1);
        this.add(But2);

        this.setSize(250,180);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        lbPass.setBounds(450, 100, 450, 100);
        Font f11 = new Font("微软雅黑", Font.BOLD, 20);
        lbPass.setFont(f11);
        lbPass.setForeground(Color.BLACK);


        But1.setBounds(180, 45, 200, 50);
        Font f6 = new Font("微软雅黑", Font.BOLD, 20);
        But1.setFont(f6);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f7 = new Font("微软雅黑", Font.BOLD, 20);
        But2.setFont(f7);
        But2.setForeground(Color.BLACK);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            sql = "SELECT * FROM goods WHERE goodsID = " + shoppingID + "";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                resultSet.next();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            int GoodsCount = 0;
            try {
                GoodsCount = resultSet.getInt("goodsCount");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String local = new String(PayPass.getPassword());
            sql = "SELECT * FROM customs WHERE customerID = " + Conf.id + " AND customerPassword = '" + local + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "购买成功~~~");
                    this.dispose();
                    if (ShopCarGoodsCount < shopCount) {
                        sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE  ownGoodsID = " + shoppingID + " AND customerID = '" + Conf.id + "'";
                        dataBase.statement.executeUpdate(sql);
                        sql = "UPDATE goods SET goodsCount = " + (GoodsCount - shopCount + ShopCarGoodsCount ) + " WHERE goodsID = " + shoppingID + "";
                        dataBase.statement.executeUpdate(sql);
                    } else {
                        sql = "UPDATE shopcar SET ownGoodsCount = " + (ShopCarGoodsCount - shopCount) + " WHERE ownGoodsID = " + shoppingID + " AND customerID = " +Conf.id + "";
                        dataBase.statement.executeUpdate(sql);
                    }
                    //更新消费等级
                    sql = "SELECT costTotal FROM customs WHERE customerID = " + Conf.id + "";
                    resultSet = dataBase.statement.executeQuery(sql);
                    resultSet.next();
                    double costTotal = resultSet.getDouble("costTotal");
                    sql = "UPDATE customs SET costTotal = " + (costTotal + total) + "WHERE customerID = " +Conf.id + "";
                    dataBase.statement.executeUpdate(sql);
                    double cost = costTotal + total;
                    if(cost > 0.0 && cost < 1000.0){
                        sql = "UPDATE customs SET customerGrade = '铜牌客户' WHERE customerID = " + Conf.id + "";
                        dataBase.statement.executeUpdate(sql);
                    }
                    if(cost > 1000.0 && cost < 5000.0){
                        sql = "UPDATE customs SET customerGrade = '银牌客户' WHERE customerID = " + Conf.id + "";
                        dataBase.statement.executeUpdate(sql);
                    }
                    if(cost > 5000.0){
                        sql = "UPDATE customs SET customerGrade = '金牌客户' WHERE customerID = " + Conf.id + " ";
                        dataBase.statement.executeUpdate(sql);
                    }
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
                    sql = "INSERT INTO shoppinghistory VALUES(" + Conf.id + "," + goodsID + ",'" + name + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + shopCount + ",'" + dateTime + "')";
                    dataBase.statement.executeUpdate(sql);
                    new CustomerOperation();
                }
                else{
                        if (count < 4) {
                            count++;
                            times--;
                            JOptionPane.showMessageDialog(this, "第" + count + "次输入的密码不正确，您还有" + times + "次机会");
                            return;
                        } else {
                            JOptionPane.showMessageDialog(this, "支付失败，请稍后重试~~~");
                            new ChoosePayMethod(ShopCarGoodsCount, shopCount, shoppingID, total);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃购买~~~");
            this.dispose();
            new CustomerOperation();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}

