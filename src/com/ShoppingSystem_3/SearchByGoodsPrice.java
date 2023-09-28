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
import java.util.Date;

/**
 /**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class SearchByGoodsPrice extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbGood = new JLabel("请输入您要搜索商品的价格下限：");
    private JTextField customerName = new JTextField(5);
    private JLabel lbGood1 = new JLabel("请输入您要搜索商品的价格上限：");
    private JTextField customerName1 = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  SearchByGoodsPrice(){
        super("搜索~~~~");
        this.setLayout(new FlowLayout());
        this.add(lbGood);
        this.add(customerName);
        this.add(lbGood1);
        this.add(customerName1);
        this.add(But1);
        this.add(But2);

        this.setSize(400,200);
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

        lbGood1.setBounds(450, 100, 450, 100);
        Font f12= new Font("微软雅黑", Font.BOLD, 20);
        lbGood1.setFont(f12);
        lbGood1.setForeground(Color.BLACK);

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
            if(customerName.getText().trim().length()!=0) {
                if (customerName1.getText().trim().length() != 0) {
                    double price1 = Double.parseDouble(customerName.getText());
                    double price2 = Double.parseDouble(customerName1.getText());
                    String sql = "SELECT * FROM  goods WHERE goodsExitPrice BETWEEN " + price1 + "  AND " + price2 + "";
                    try {
                        resultSet = dataBase.statement.executeQuery(sql);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        if (resultSet.next()) {
                            sql = "SELECT * FROM  goods WHERE goodsExitPrice BETWEEN " + price1 + "  AND " + price2 + "";
                            resultSet = dataBase.statement.executeQuery(sql);
                            System.out.println("*********************以下为为您找到的全部商品信息*********************");
                            System.out.println("商品编号\t商品名\t生产商\t\t生产日期\t\t产品型号\t零售价\t库存\t");
                            while (resultSet.next()) { // 让光标向后移动，如果没有更多行，则返回 false
                                int id = resultSet.getInt("goodsID");
                                String name = resultSet.getString("goodsName");
                                String manufacturer = resultSet.getString("goodsManufacture");
                                Date date = resultSet.getDate("goodsDateOfProduction");
                                String type = resultSet.getString("goodsTypes");
                                double importPrice = resultSet.getDouble("goodsImportPrice");
                                double ExitPrice = resultSet.getDouble("goodsExitPrice");
                                int count = resultSet.getInt("goodsCount");
                                System.out.println("\t" + id + "\t" + name + "\t" + manufacturer + "\t\t" + date + "\t" + type + "\t" + ExitPrice + "\t" + count + "\t");
                            }
                            System.out.println("***************************************************************");
                        } else {
                            JOptionPane.showMessageDialog(this, "暂无此价格区间的商品的任何信息！");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(this,"价格下限为空！");
                }
            }else{
                JOptionPane.showMessageDialog(this,"价格上限为空！");
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃搜索~~~");
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

