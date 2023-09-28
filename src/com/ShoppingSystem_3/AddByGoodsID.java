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
public class AddByGoodsID extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbGood = new JLabel(" 请输入你要购买的商品ID:");
    private JTextField goodsID = new JTextField(20);
    private JLabel lbGoodNum = new JLabel("请输入你要购买的商品数量:");
    private JTextField goodsNum = new JTextField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  AddByGoodsID(){
        super("商品ID添加~~~~");
        this.setLayout(new FlowLayout());
        this.add(lbGood);
        this.add(goodsID);
        this.add(lbGoodNum);
        this.add(goodsNum);
        this.add(But1);
        this.add(But2);

        this.setSize(250,200);
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

        lbGoodNum.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 20);
        lbGoodNum.setFont(f);
        lbGoodNum.setForeground(Color.BLACK);

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
            if(goodsID.getText().trim().length()!=0) {
                int buyThingsID = Integer.parseInt(goodsID.getText());
                sql = "SELECT * FROM goods WHERE goodsID = '" + buyThingsID + "' ";
                try {
                    resultSet = dataBase.statement.executeQuery(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (resultSet.next()) {
                        String buyThings = resultSet.getString("goodsName");
                        String manufacturer = resultSet.getString("goodsManufacture");
                        Date date = resultSet.getDate("goodsDateOfProduction");
                        String type = resultSet.getString("goodsTypes");
                        double importPrice = resultSet.getDouble("goodsImportPrice");
                        double ExitPrice = resultSet.getDouble("goodsExitPrice");
                        int restGoodsCount = resultSet.getInt("goodsCount");
                        JOptionPane.showMessageDialog(this, "您购买的商品还剩余：" + restGoodsCount);
                        if(goodsNum.getText().trim().length()!=0) {
                            int number = Integer.parseInt(goodsNum.getText());
                            if (number > restGoodsCount) {
                                JOptionPane.showMessageDialog(this, "商品库存不足，请重新输入：");
                            } else {
                                //从商品库中减去被购买商品的数量
                                int newGoodsCount = restGoodsCount - number;
                                JOptionPane.showMessageDialog(this, "商品已成功添加至购物车，快去为宝贝买单吧！");

                                String sql2 = "SELECT ownGoodsCount FROM shopcar WHERE  customerID = '" + Conf.id + "' AND ownGoodsID = '" + buyThingsID + "' ";
                                ResultSet resultSet2 = dataBase.statement.executeQuery(sql2);
                                if (resultSet2.next()) {
                                    int ownGoodsCount = resultSet2.getInt("ownGoodsCount");
                                    sql = "UPDATE shopcar SET ownGoodsCount = " + (ownGoodsCount + number) + " WHERE ownGoodsID = '" + buyThingsID + "' AND customerID = '" + Conf.id + "'";
                                    dataBase.statement.executeUpdate(sql);
                                } else {
                                    sql = "INSERT INTO shopcar VALUES(" + Conf.id + "," + buyThingsID + ",'" + buyThings + "','" + manufacturer + "','" + date + "','" + type + "'," + ExitPrice + "," + number + ")";
                                    dataBase.statement.executeUpdate(sql);
                                }
                                sql = "UPDATE goods SET goodsCount = " + (restGoodsCount - number) + " WHERE goodsName = '" + buyThings + "'";
                                dataBase.statement.executeUpdate(sql);
                                this.dispose();
                                new ShoppingMg().printShopCar(Conf.id);
                                new ShoppingCarMg();
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "请输入商品数目");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "没有找到此商品，请重新输入~~~~~");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "输入ID为空~~~");
            }
        }
        if(e.getSource()==But2){
            this.dispose();
            JOptionPane.showMessageDialog(this, "放弃添加~~~");
            new ShoppingCarMg();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}


