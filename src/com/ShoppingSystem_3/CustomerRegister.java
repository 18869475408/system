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
import java.time.LocalDateTime;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class CustomerRegister extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbName = new JLabel("请输入您的用户名:");
    private JTextField customerName = new JTextField(20);
    private JLabel lbPassword = new JLabel("\t请设置您的登录密码:\n");
    private JPasswordField customerPassword = new JPasswordField(20);
    private JLabel lbPassword1 = new JLabel("\t确认您的登录密码:\n");
    private JPasswordField customerPassword1 = new JPasswordField(20);
    private JLabel lbPhone = new JLabel("\t绑定手机号：");
    private JTextField customerPhone = new JTextField(20);
    private JLabel lbEmail = new JLabel("\t绑定邮箱账号：");
    private JTextField customerEmail = new JTextField(25);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  CustomerRegister(){
        super("用户注册");
        this.setLayout(new FlowLayout());
        this.add(lbName);
        this.add(customerName);
        this.add(lbPassword);
        this.add(customerPassword);
        this.add(lbPassword1);
        this.add(customerPassword1);
        this.add(lbPhone);
        this.add(customerPhone);
        this.add(lbEmail);
        this.add(customerEmail);
        this.add(But1);
        this.add(But2);
        this.setSize(500,360);
        toCenter(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1400,200);
        this.setVisible(true);
        But1.addActionListener(this);
        But2.addActionListener(this);

        lbName.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 22);
        lbName.setFont(f);
        lbName.setForeground(Color.BLACK);

        lbPassword.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 22);
        lbPassword.setFont(f1);

        lbPassword1.setForeground(Color.BLACK);
        lbPassword1.setBounds(180, 45, 450, 100);
        Font f6 = new Font("微软雅黑", Font.BOLD, 22);
        lbPassword1.setFont(f6);
        lbPassword1.setForeground(Color.BLACK);

        lbPhone.setBounds(180, 45, 450, 80);
        Font f2 = new Font("微软雅黑", Font.BOLD, 22);
        lbPhone.setFont(f2);
        lbPhone.setForeground(Color.BLACK);

        lbEmail.setBounds(180, 45, 450, 80);
        Font f3 = new Font("微软雅黑", Font.BOLD, 22);
        lbEmail.setFont(f3);
        lbEmail.setForeground(Color.BLACK);

        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 22);
        But1.setFont(f4);
       But1.setForeground(Color.BLACK);

       But2.setBounds(180, 45, 200, 50);
        Font f5 = new Font("微软雅黑", Font.BOLD, 22);
        But2.setFont(f5);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;

            String customersName1 = customerName.getText();
            if (customersName1.length() < 5) {
                JOptionPane.showMessageDialog(this, "用户名长度不少于5个字符，请重新输入：");
                return;
            }
            sql = "SELECT * FROM customs WHERE customerName = '" + customersName1 + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "该名称已有小伙伴使用啦，请换一个吧~");
                    flag1 = false;
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

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

            String phoneNumber = customerPhone.getText();
            if (!phoneNumber.matches("1[3-9]\\d{9}")) {
                JOptionPane.showMessageDialog(this, "该手机号不是一个有效的手机号！请换个手机号重试： ");
                flag2 = false;
            }
            sql = "SELECT customerPhoneNumber FROM customs WHERE customerPhoneNumber = '" + phoneNumber + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "该号码已经被注册！请换个号码重试");
                    flag2 = false;
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            String emailInput = customerEmail.getText();
            if (!emailInput.matches("\\w{1,30}@[a-zA-Z0-9]{2,20}(\\.[a-zA-Z0-9]{2,20}){1,2}")) {
                JOptionPane.showMessageDialog(this, "该邮箱账.号不是一个有效的邮箱号！请换个邮箱账号重试： ");
                flag3 = false;
            }
            sql = "SELECT customerEmail FROM customs WHERE customerEmail = '" + emailInput + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "该邮箱账号已经被注册！请换个邮箱账号重试");
                    flag3 = false;
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (customersName1.length() > 4 && flag1 == true && (cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    (cusPassword2.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    cusPassword1.equals(cusPassword2) && flag2 == true && flag3 == true) {
                LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
                JOptionPane.showMessageDialog(this,  "您于" + dateTime + "注册了账号");
                int id = (int)(Math.random()*96+4);
                sql = "SELECT * FROM customs WHERE customerID = ' + id + '";
               while(true) {
                   try {
                       resultSet = dataBase.statement.executeQuery(sql);
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }
                   try {
                       if (resultSet.next()) {
                           id = (int)(Math.random()*96+4);
                       }
                       else{
                           break;
                       }
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }
               }
                sql = "INSERT INTO customs VALUES ("+id+",'"+customersName1+"','"+cusPassword1+"',"+"'铜牌客户'"+",'"+dateTime+"',"+0.0f+",'"+emailInput+"', '"+phoneNumber+"')";
                try {
                    dataBase.statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
                new CustomerHome();
            }
        }
        if(e.getSource()==But2){
            this.dispose();
            JOptionPane.showMessageDialog(this, "放弃注册！");
            new CustomerHome();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
