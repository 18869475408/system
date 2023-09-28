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
public class CustomerOperation extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet ;
    private JLabel choose1 = new JLabel("  1--密码管理       ");
    private JLabel choose2 = new JLabel("  2--购物车管理      ");
    private JLabel choose3 = new JLabel("  3--商品支付        ");
    private JLabel choose4 = new JLabel("  4-查看购物历史      ");
    private JLabel choose5 = new JLabel("  5--商品浏览         ");
    private JLabel choose6 = new JLabel("  6--商品搜索         ");
    private JLabel choose7 = new JLabel("  7--退出登录         ");
    private JLabel choose = new JLabel("  请选择您接下来的操作： ");
    private JTextField cho = new JTextField(10);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public CustomerOperation(){
        super("欢迎您来到用户操作界面~~~~");
        this.setLayout(new FlowLayout());
        this.add(choose1);
        this.add(choose2);
        this.add(choose3);
        this.add(choose4);
        this.add(choose5);
        this.add(choose6);
        this.add(choose7);
        this.add(choose);
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

        choose.setBounds(450, 100, 450, 100);
        Font f11 = new Font("微软雅黑", Font.BOLD, 20);
        choose.setFont(f11);
        choose.setForeground(Color.BLACK);

        choose1.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 23);
        choose1.setFont(f);
        choose1.setForeground(Color.BLACK);

        choose2.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 23);
        choose2.setFont(f1);
        choose2.setForeground(Color.BLACK);

        choose3.setBounds(180, 45, 450, 80);
        Font f3= new Font("微软雅黑", Font.BOLD, 23);
        choose3.setFont(f3);
        choose3.setForeground(Color.BLACK);

        choose4.setBounds(180, 45, 450, 100);
        Font f4 = new Font("微软雅黑", Font.BOLD, 23);
        choose4.setFont(f4);
        choose4.setForeground(Color.BLACK);

        choose5.setBounds(180, 45, 450, 80);
        Font f5 = new Font("微软雅黑", Font.BOLD, 23);
        choose5.setFont(f5);
        choose5.setForeground(Color.BLACK);

        choose7.setBounds(180, 45, 450, 100);
        Font f10= new Font("微软雅黑", Font.BOLD, 23);
        choose7.setFont(f10);
        choose7.setForeground(Color.BLACK);

        choose6.setBounds(180, 45, 450, 80);
        Font f18 = new Font("微软雅黑", Font.BOLD, 23);
        choose6.setFont(f18);
        choose6.setForeground(Color.BLACK);


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
            if(cho.getText().trim().length()!=0) {
                int choose = Integer.parseInt(cho.getText());
                if (choose > 0 && choose < 8) {
                    switch (choose) {
                        case 1:
                            this.dispose();
                            new ModifyPasswordChoose();
                            break;
                        case 2:
                            this.dispose();
                            try {
                                new Goods().showAllGoods();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            try {
                                new ShoppingMg().printShopCar(Conf.id);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            new ShoppingCarMg();
                            break;
                        case 3:
                            this.dispose();
                            try {
                                new ShoppingMg().printShopCar(Conf.id);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            new PayInterface();
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(this, "您的购物历史如下~~~");
                            try {
                                new ShoppingHs().getShoppingHs(Conf.id);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case 5:
                            try {
                                new Goods().showAllGoods();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            JOptionPane.showMessageDialog(this, "全部商品信息如下~~~");
                            break;
                        case 6:
                            this.dispose();
                            new SearchGood();
                            break;
                        case 7:
                            this.dispose();
                            new CustomerHome();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请重新输入~~~");
                }
            }else{
                JOptionPane.showMessageDialog(this, "输入为空~~~");
            }
            }

        if(e.getSource()==But2){
           this.dispose();
           new Home();
        }

    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
