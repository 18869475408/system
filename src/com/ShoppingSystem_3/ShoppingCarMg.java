package com.ShoppingSystem_3;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class ShoppingCarMg extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet ;
    int id;
    private JLabel choose1 = new JLabel("  1--添加商品至购物车          ");
    private JLabel choose2 = new JLabel("  2--删除购物车中的商品        ");
    private JLabel choose3 = new JLabel("  3---自动清理               ");
    private JLabel choose = new JLabel(" 请选择对购物车的操作");
    private JTextField cho = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  ShoppingCarMg(){
        super("欢迎您来到购物车管理界面~~~~");
        this.id = id;
        this.setLayout(new FlowLayout());
        this.add(choose1);
        this.add(choose2);
        this.add(choose3);
        this.add(choose);
        this.add(cho);
        this.add(But1);
        this.add(But2);

        this.setSize(300,250);
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

        choose3.setBounds(180, 45, 450, 80);
        Font f3= new Font("微软雅黑", Font.BOLD, 20);
        choose3.setFont(f3);
        choose3.setForeground(Color.BLACK);


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
            if(cho.getText().trim().length()!=0) {
                int choose = Integer.parseInt(cho.getText());
                if (choose > 0 && choose < 4) {
                    switch (choose) {
                        case 1:
                            this.dispose();
                            new AddGoodToShoppingCar();
                            break;
                        case 2:
                            this.dispose();
                            new DeleteGoodsFromShopCar();
                            break;
                        case 3:
                            try {
                                new ShoppingMg().modifyGoodsOfShopCar();
                                JOptionPane.showMessageDialog(this, "购物车清理成功~~~");
                                new ShoppingMg().printShopCar(Conf.id);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            this.dispose();
                            new ShoppingCarMg();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请重新输入~~~");
                }
            }else{
                JOptionPane.showMessageDialog(this, "输入为空~~~");
            }
        }

        if (e.getSource() == But2) {
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
