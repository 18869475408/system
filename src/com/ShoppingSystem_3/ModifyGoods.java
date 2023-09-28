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
public class ModifyGoods extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int modifyGoodsID;
    private JLabel lbName = new JLabel("1--商品名：");
    private JTextField goodsNam = new JTextField(20);

    private JLabel lbID = new JLabel("2--商品编号：");
    private JTextField goodsNum = new JTextField(20);

    private JLabel lbM = new JLabel("3--商品生产厂商：");
    private JTextField goodsMft = new JTextField(20);

    private JLabel lbD = new JLabel("4--商品生产日期：");
    private JTextField goodsDP = new JTextField(20);

    private JLabel lbT = new JLabel("5--商品型号：");
    private JTextField goodsTp = new JTextField(20);

    private JLabel lbI = new JLabel("6--商品进价：");
    private JTextField goodsIP = new JTextField(20);

    private JLabel lbO = new JLabel("7--商品零售价：");
    private JTextField goodsEP = new JTextField(20);

    private JLabel lbC = new JLabel("8--商品数量：");
    private JTextField goodsCt = new JTextField(20);

    private JLabel lbchoose = new JLabel("请选择修改信息编号并在编号后填写修改过的信息：");
    private JTextField cho = new JTextField(2);

    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  ModifyGoods(int modifyGoodsID){
        super("商品修改");
        this.modifyGoodsID=modifyGoodsID;
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
        this.add(lbchoose);
        this.add(cho);
        this.add(But1);
        this.add(But2);
        this.setSize(400,400);
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

        lbchoose.setBounds(450, 100, 450, 100);
        Font f10 = new Font("微软雅黑", Font.BOLD, 15);
        lbchoose.setFont(f10);
        lbchoose.setForeground(Color.BLACK);

        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 15);
        But1.setFont(f4);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f9 = new Font("微软雅黑", Font.BOLD, 15);
        But2.setFont(f9);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == But1) {
            if(cho.getText().trim().length()!=0) {
                int choose = Integer.parseInt(cho.getText());
                if (0 < choose && 9 > choose) {
                    switch (choose) {
                        case 2://修改商品编号
                            int alter = Integer.parseInt(goodsNum.getText());
                            sql = "SELECT goodsID FROM goods WHERE goodsID = " + alter + "";
                            try {
                                resultSet = dataBase.statement.executeQuery(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            while (true) {
                                try {
                                    if (!resultSet.next()) break;
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                sql = "SELECT goodsID FROM goods WHERE goodsID = " + alter + "";
                                try {
                                    resultSet = dataBase.statement.executeQuery(sql);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                JOptionPane.showMessageDialog(this, "该商品编号已有商品，请重新输入：");
                            }
                            sql = "UPDATE  goods SET goodsID = '" + alter + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 1://修改商品名称
                            String alter1 = goodsNam.getText();
                            sql = "UPDATE goods SET goodsName = '" + alter1 + "'WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 3://修改商品生产厂家
                            String alter2 = goodsMft.getText();
                            sql = "UPDATE  goods SET goodsManufacture = '" + alter2 + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 4://修改生产日期
                            String goodsDP11 = goodsDP.getText();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date parse = null;
                            try {
                                parse = dateFormat.parse(goodsDP11);
                            } catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            }
                            java.sql.Date goodsDP1 = new java.sql.Date(parse.getTime());
                            sql = "UPDATE  goods SET goodsDateOfProduction = '" + goodsDP1 + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 5://修改产品型号
                            String alter4 = goodsTp.getText();
                            sql = "UPDATE  goods SET goodsTypes = '" + alter4 + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 6://修改商品进价
                            double alter5 = Double.parseDouble(goodsIP.getText());
                            sql = "UPDATE  goods SET goodsImportPrice = '" + alter5 + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 7://修改商品零售价
                            double alter6 = Double.parseDouble(goodsEP.getText());
                            sql = "UPDATE goods SET goodsImportPrice = '" + alter6 + "' WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                        case 8://修改商品数量
                            int alter7 = Integer.parseInt(goodsCt.getText());
                            sql = "UPDATE goods SET goodsCount = '" + alter7 + "' WHERE WHERE goodsID = " + modifyGoodsID + "";
                            try {
                                dataBase.statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "商品修改成功~~~~");
                            new GoodsManagement();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请重新输入~~~~");
                }
            }else{
                JOptionPane.showMessageDialog(this, "输入为空~~~~");
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃修改！");
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
