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
public class PartialDeletion extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int delGoodsID;
    private JLabel lbNum = new JLabel("请输入需要删除的商品数量：");
    private JTextField goodsNum = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  PartialDeletion(int delGoodsID){
        super("部分删除");
        this.delGoodsID = delGoodsID ;
        this.setLayout(new FlowLayout());
        this.add(lbNum);
        this.add(goodsNum);
        this.add(But1);
        this.add(But2);
        this.setSize(350,180);
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
            int restOwnGoodsCount = 0;
            int restGoodsCount = 0;
            sql = "SELECT * FROM shopcar WHERE ownGoodsID = '" + delGoodsID + "' AND customerID = '" + Conf.id + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                resultSet.next();
                restOwnGoodsCount = resultSet.getInt("ownGoodsCount");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            sql = "SELECT * FROM goods WHERE goodsID = '" + delGoodsID + "' ";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    try {
                        restGoodsCount = resultSet.getInt("goodsCount");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (goodsNum.getText().trim().length() != 0) {
                int deleteCount = Integer.parseInt(goodsNum.getText());
                if (deleteCount > restOwnGoodsCount || deleteCount <= 0) {
                    JOptionPane.showMessageDialog(this, "数量不足或输入有误，请重新输入~~~:");
                } else {
                    sql = "UPDATE shopcar SET ownGoodsCount = " + (restOwnGoodsCount - deleteCount) + "  WHERE ownGoodsID = '" + delGoodsID + "' AND customerID = " + Conf.id + "";
                    try {
                        dataBase.statement.executeUpdate(sql);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    sql = "UPDATE goods SET goodsCount = " + (restGoodsCount + deleteCount) + " WHERE goodsID = '" + delGoodsID + "'";
                    try {
                        dataBase.statement.executeUpdate(sql);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(this, "删除成功~~~");
                    this.dispose();
                    try {
                        new ShoppingMg().printShopCar(Conf.id);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    new ShoppingCarMg();
                }
            } else {
                JOptionPane.showMessageDialog(this, "请输入商品数目~~~");
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃删除~~~");
            new DeleteGoodsFromShopCar();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
