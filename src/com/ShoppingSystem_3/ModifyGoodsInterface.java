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
public class ModifyGoodsInterface extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int delGoodsID;
    private JLabel lbID = new JLabel("请输入您想修改的产品编号：");
    private JTextField goodsID = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  ModifyGoodsInterface(){
        super("商品修改");
        this.setLayout(new FlowLayout());
        this.add(lbID);
        this.add(goodsID);
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

        lbID.setBounds(450, 100, 450, 100);
        Font f2 = new Font("微软雅黑", Font.BOLD, 20);
        lbID.setFont(f2);
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
            if(goodsID.getText().trim().length()!=0){
            int modifyGoodsID = Integer.parseInt(goodsID.getText());
                sql = "SELECT * FROM goods WHERE goodsID = " + modifyGoodsID + "";
                try {
                    resultSet = dataBase.statement.executeQuery(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (resultSet.next()) {
                        this.dispose();
                        new ModifyGoods(modifyGoodsID);
                    } else {
                        JOptionPane.showMessageDialog(this, "找不到该商品信息，请重新输入~~~");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "输入为空~~~");
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃修改~~~");
            new ShoppingCarMg();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}