package com.ShoppingSystem_3;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class PayInterface extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int delGoodsID;
    private JLabel lbID = new JLabel("请输入您想购买的产品编号：");
    private JTextField goodsID = new JTextField(5);
    private JLabel lbNum = new JLabel("请输入您想购买的产品数量：");
    private JTextField goodsNum = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  PayInterface(){
        super("购买页面");
        this.setLayout(new FlowLayout());
        this.add(lbID);
        this.add(goodsID);
        this.add(lbNum);
        this.add(goodsNum);
        this.add(But1);
        this.add(But2);
        this.setSize(350,200);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        lbNum.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 20);
        lbNum.setFont(f);
        lbNum.setForeground(Color.BLACK);

        lbID.setBounds(450, 100, 450, 100);
        Font f2 = new Font("微软雅黑", Font.BOLD, 20);
        lbID.setFont(f);
        lbID.setForeground(Color.BLACK);
        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 20);
        But1.setFont(f4);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f5 = new Font("微软雅黑", Font.BOLD, 20);
        But2.setFont(f4);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            boolean flag1 = true;
            boolean flag2 = true;
            if (goodsID.getText().trim().length() != 0) {
                int shoppingID = Integer.parseInt(goodsID.getText());
                sql = "SELECT * FROM shopcar WHERE ownGoodsID =" + shoppingID + " AND  customerID = " + Conf.id + "";
                try {
                    resultSet = dataBase.statement.executeQuery(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "从您的购物车中找不到该商品！");
                        flag1 = false;
                    } else {
                        if (goodsNum.getText().trim().length() != 0) {
                            int shopCount = Integer.parseInt(goodsNum.getText());
                            sql = "SELECT * FROM shopcar WHERE ownGoodsID = " + shoppingID + "  AND  customerID = " + Conf.id + "";
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
                            int ShopCarGoodsCount = 0;
                            try {
                                ShopCarGoodsCount = resultSet.getInt("ownGoodsCount");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

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

                            double price = 0;
                            try {
                                price = resultSet.getDouble("goodsExitPrice");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            double total = shopCount * price;//总价
                            if (ShopCarGoodsCount + GoodsCount < shopCount) {
                                JOptionPane.showMessageDialog(this, "商品库存不足，请重新输入：");
                                flag2 = false;
                            }
                            if (flag1 == true && flag2 == true) {
                                this.dispose();
                                new ChoosePayMethod(ShopCarGoodsCount, shopCount, shoppingID, total);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "请输入商品数目");
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "请输入商品ID");
            }
        }
        if(e.getSource()==But2){
            this.dispose();
            JOptionPane.showMessageDialog(this, "放弃购买~~~");
            new ShoppingCarMg();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
