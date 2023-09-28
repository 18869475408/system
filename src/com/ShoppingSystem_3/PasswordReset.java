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
public class PasswordReset extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    int id;
    private JLabel lbPassword1 = new JLabel("请设置您的登录密码:");
    private JPasswordField customerPassword = new JPasswordField(20);
    private JLabel lbPassword2 = new JLabel("确认您的登录密码:");
    private JPasswordField customerPassword1 = new JPasswordField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  PasswordReset(int id){
        super("密码修改~~~~");
        this.id = id;
        this.setLayout(new FlowLayout());
        this.add(lbPassword1);
        this.add(customerPassword);
        this.add(lbPassword2);
        this.add(customerPassword1);
        this.add(But1);
        this.add(But2);

        this.setSize(250,250);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        lbPassword1.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 15);
        lbPassword1.setFont(f);
        lbPassword1.setForeground(Color.BLACK);

        But1.setBounds(180, 45, 200, 50);
        Font f6 = new Font("微软雅黑", Font.BOLD, 15);
        But1.setFont(f6);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f7 = new Font("微软雅黑", Font.BOLD, 15);
        But2.setFont(f7);
        But2.setForeground(Color.BLACK);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            String cusPassword1 = new String(customerPassword.getPassword());
            if (!(cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$"))) {
                JOptionPane.showMessageDialog(this, "密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合，请重新输入：");
                return;
            }

            String cusPassword2 = new String(customerPassword1.getPassword());
            if (!(cusPassword2.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$"))) {
                JOptionPane.showMessageDialog(this, "密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合，请重新输入：");
                return;
            }
            if ((cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    (cusPassword2.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    !cusPassword1.equals(cusPassword2)) {
                JOptionPane.showMessageDialog(this, "两次密码输入不一致！");
                return;
            }
            if ((cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    (cusPassword2.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    cusPassword1.equals(cusPassword2)) {
                sql = "UPDATE customs SET customerPassword = '"+ cusPassword2 +"' WHERE customerID = "+Conf.id +"";
                try {
                    dataBase.statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "密码修改成功，请重新登录~~~");
                this.dispose();
                new CustomerLogin();
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃修改~~~");
            this.dispose();
            new CustomerLogin();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
