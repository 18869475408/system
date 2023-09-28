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
public class DeleteByGoodsID extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbGood = new JLabel("请输入你要删除的商品ID:");
    private JTextField goodsID = new JTextField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  DeleteByGoodsID(){
        super("商品ID删除~~~~");
        this.setLayout(new FlowLayout());
        this.add(lbGood);
        this.add(goodsID);
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

        lbGood.setBounds(450, 100, 450, 100);
        Font f11 = new Font("微软雅黑", Font.BOLD, 20);
        lbGood.setFont(f11);
        lbGood.setForeground(Color.BLACK);


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
            if((goodsID.getText().trim().length()!=0)){
            int delGoodsID = Integer.parseInt(goodsID.getText());
            sql = "SELECT * FROM shopcar WHERE ownGoodsID = '" + delGoodsID + "' AND customerID = " + Conf.id + "";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    this.dispose();
                    new DeleteMethod(delGoodsID);
                }
                else{
                    JOptionPane.showMessageDialog(this,"购物车中没有此商品,请重新选择！");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
              }
            }
            else{
                JOptionPane.showMessageDialog(this,"输入为空！");
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃添加~~~");
            this.dispose();
            new ShoppingCarMg();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}

