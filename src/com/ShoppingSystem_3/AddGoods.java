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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class AddGoods extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;

    private JLabel lbName = new JLabel("请输入添加商品名：");
    private JTextField goodsNam = new JTextField(20);

    private JLabel lbID = new JLabel("请输入添加商品的编号：");
    private JTextField goodsNum = new JTextField(20);

    private JLabel lbM = new JLabel("请输入添加商品生产厂商：");
    private JTextField goodsMft = new JTextField(20);

    private JLabel lbD = new JLabel("请输入添加商品生产日期：");
    private JTextField goodsDP = new JTextField(20);

    private JLabel lbT = new JLabel("请输入添加商品型号：");
    private JTextField goodsTp = new JTextField(20);

    private JLabel lbI = new JLabel("请输入添加商品进价：");
    private JTextField goodsIP = new JTextField(20);

    private JLabel lbO = new JLabel("请输入添加商品零售价：");
    private JTextField goodsEP = new JTextField(20);

    private JLabel lbC = new JLabel("请输入添加商品数量： ");
    private JTextField goodsCt = new JTextField(25);

    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  AddGoods(){
        super("商品上架");
        this.setLayout(new FlowLayout());
        this.add(lbName);
        this.add(goodsNam);
        this.add(lbID);
        this.add(goodsNum);
        this.add(lbM);
        this.add(goodsMft);
        this.add(lbD);
        this.add(goodsDP);
        this.add(lbT);
        this.add(goodsTp);
        this.add(lbI);
        this.add(goodsIP);
        this.add(lbO);
        this.add(goodsEP);
        this.add(lbC);
        this.add(goodsCt);
        this.add(But1);
        this.add(But2);
        this.setSize(500,400);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        lbName.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 15);
        lbName.setFont(f);
        lbName.setForeground(Color.BLACK);

        lbID.setBounds(450, 100, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 15);
        lbID.setFont(f1);
        lbID.setForeground(Color.BLACK);

        lbM.setBounds(450, 100, 450, 100);
        Font f2 = new Font("微软雅黑", Font.BOLD, 15);
        lbM.setFont(f2);
        lbM.setForeground(Color.BLACK);

        lbD.setBounds(450, 100, 450, 100);
        Font f3 = new Font("微软雅黑", Font.BOLD, 15);
        lbD.setFont(f3);
        lbD.setForeground(Color.BLACK);

        lbT.setBounds(450, 100, 450, 100);
        Font f5 = new Font("微软雅黑", Font.BOLD, 15);
        lbT.setFont(f5);
        lbT.setForeground(Color.BLACK);

        lbI.setBounds(450, 100, 450, 100);
        Font f6 = new Font("微软雅黑", Font.BOLD, 15);
        lbI.setFont(f6);
        lbI.setForeground(Color.BLACK);

        lbO.setBounds(450, 100, 450, 100);
        Font f7 = new Font("微软雅黑", Font.BOLD, 15);
        lbO.setFont(f7);
        lbO.setForeground(Color.BLACK);

        lbC.setBounds(450, 100, 450, 100);
        Font f8 = new Font("微软雅黑", Font.BOLD, 15);
        lbC.setFont(f8);
        lbC.setForeground(Color.BLACK);


        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 15);
        But1.setFont(f4);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f9 = new Font("微软雅黑", Font.BOLD, 15);
        But2.setFont(f9);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            if(goodsNum.getText().length()!=0) {
                if (goodsIP.getText().trim().length() != 0) {
                    if (goodsEP.getText().trim().length() != 0) {
                        if (goodsCt.getText().trim().length() != 0) {
                            boolean flag = true;
                            String goodsNam1 = goodsNam.getText();
                            int goodsNum1 = Integer.parseInt(goodsNum.getText());
                            sql = "SELECT goodsID FROM goods WHERE goodsID = " + goodsNum1 + "";
                            try {
                                resultSet = dataBase.statement.executeQuery(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                if (resultSet.next()) {
                                    sql = "SELECT goodsID FROM goods WHERE goodsID = " + goodsNum1 + "";
                                    try {
                                        resultSet = dataBase.statement.executeQuery(sql);
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    JOptionPane.showMessageDialog(this, "该商品编号已有商品，请重新输入：");
                                    flag = false;
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            String goodsMft1 = goodsMft.getText();
                            String goodsDP11 = goodsDP.getText();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date parse = null;
                            try {
                                parse = dateFormat.parse(goodsDP11);
                            } catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            }
                            java.sql.Date goodsDP1 = new java.sql.Date(parse.getTime());
                            String goodsTp1 = goodsTp.getText();
                            Double goodsIP1 = Double.parseDouble(goodsIP.getText());
                            Double goodsEP1 = Double.parseDouble(goodsEP.getText());
                            int goodsCt1 = Integer.parseInt(goodsCt.getText());

                            if (flag == true) {
                                this.dispose();
                                JOptionPane.showMessageDialog(this, "商品添加成功！");
                                sql = "INSERT INTO goods VALUES('" + goodsNam1 + "'," + goodsNum1 + ",'" + goodsMft1 + "','" + goodsDP1 + "','" + goodsTp1 + "'," + goodsIP1 + "," + goodsEP1 + "," + goodsCt1 + ")";
                                try {
                                    dataBase.statement.executeUpdate(sql);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                new GoodsManagement();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "商品数量为空！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "商品数量为空！");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "商品数量为空！");
                }
            }
                        else{
                            JOptionPane.showMessageDialog(this, "商品数量为空！");
                        }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃添加！");
            this.dispose();
            new GoodsManagement();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
