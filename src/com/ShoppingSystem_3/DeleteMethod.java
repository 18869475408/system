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

/**
 /**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class DeleteMethod extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int delGoodsID;
    private JLabel choose1 = new JLabel("  1--清除该商品的全部数量  ");
    private JLabel choose2 = new JLabel("  2--选择性清除 ");
    private JLabel choose = new JLabel("  请选择：");
    private JButton But1 = new JButton("1");
    private JButton But2 = new JButton("2");

    public  DeleteMethod(int delGoodsID) {
        super("删除方式~~~~");
        this.delGoodsID = delGoodsID ;
        this.setLayout(new FlowLayout());
        this.add(choose1);
        this.add(choose2);
        this.add(choose);
        this.add(But1);
        this.add(But2);
        this.setSize(250, 180);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        choose.setBounds(450, 100, 450, 100);
        Font f11 = new Font("微软雅黑", Font.BOLD, 20);
        choose.setFont(f11);
        choose.setForeground(Color.BLACK);

        choose1.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 20);
        choose1.setFont(f);
        choose1.setForeground(Color.BLACK);

        choose2.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 20);
        choose2.setFont(f1);
        choose2.setForeground(Color.BLACK);


        But1.setBounds(180, 45, 200, 50);
        Font f6 = new Font("微软雅黑", Font.BOLD, 20);
        But1.setFont(f6);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f7 = new Font("微软雅黑", Font.BOLD, 20);
        But2.setFont(f7);
        But2.setForeground(Color.BLACK);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == But1) {
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
            sql = "SELECT * FROM goods WHERE goodsID = " + delGoodsID + "";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if(resultSet.next()){
                    try {
                        restGoodsCount = resultSet.getInt("goodsCount");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            sql = "UPDATE shopcar SET ownGoodsCount = " + 0 + " WHERE ownGoodsID = '" + delGoodsID + "' AND customerID = '" + Conf.id + "'";
            try {
                dataBase.statement.executeUpdate(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            sql = "UPDATE goods SET goodsCount = " + (restGoodsCount + restOwnGoodsCount) + " WHERE goodsID = '" + delGoodsID + "'";
            try {
                dataBase.statement.executeUpdate(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "删除成功~~~");
            try {
                new ShoppingMg().printShopCar(Conf.id);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
            new ShoppingCarMg();
        }
        if (e.getSource() == But2) {
            this.dispose();
            new PartialDeletion(delGoodsID);
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
