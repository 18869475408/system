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
public class ModifyPasswordChoose extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel customer = new JLabel("         1--重置密码                  ");
    private JLabel admin = new JLabel("          2--修改密码                ");
    private JLabel choose = new JLabel("请选择:" );
    private JButton But1 = new JButton("1");
    private JButton But2 = new JButton("2");

    public  ModifyPasswordChoose(){
        super("修改方式选择");
        this.setLayout(new FlowLayout());
        this.add(customer);
        this.add(admin);
        this.add(choose);
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

        customer.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 20);
        customer.setFont(f);
        customer.setForeground(Color.BLACK);

        admin.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 20);
        admin.setFont(f1);
        admin.setForeground(Color.BLACK);

        choose.setBounds(180, 45, 450, 80);
        Font f2 = new Font("微软雅黑", Font.BOLD, 20);
        choose.setFont(f2);
        choose.setForeground(Color.BLACK);

        But1.setBounds(180, 45, 200, 50);
        Font f3 = new Font("微软雅黑", Font.BOLD, 20);
        But1.setFont(f3);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 20);
        But2.setFont(f4);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1){

            try {
                new SendPassword().SendPasswordToCustomer();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
            JOptionPane.showMessageDialog(this, "密码重置成功，请重新登录~~~");
            new CustomerLogin();
                }
        if(e.getSource()==But2){
            this.dispose();
            new ModifyPassword(Conf.id);
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}


