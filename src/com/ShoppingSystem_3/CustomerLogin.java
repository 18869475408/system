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

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class CustomerLogin extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbName = new JLabel("请输入您的用户名:");
    private JTextField customerName = new JTextField(20);
    private JLabel lbPassword = new JLabel("\t请输入您的登录密码:\n");
    private JPasswordField customerPassword = new JPasswordField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("忘记密码");

    public  CustomerLogin(){
        super("用户登录");
        this.setLayout(new FlowLayout());
        this.add(lbName);
        this.add(customerName);
        this.add(lbPassword);
        this.add(customerPassword);
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

        lbName.setBounds(450, 100, 450, 100);
        Font f = new Font("微软雅黑", Font.BOLD, 15);
        lbName.setFont(f);
        lbName.setForeground(Color.BLACK);

        lbPassword.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 15);
        lbPassword.setFont(f1);

        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 15);
        But1.setFont(f4);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f5 = new Font("微软雅黑", Font.BOLD, 15);
        But2.setFont(f4);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            String name1 = customerName.getText();
            Conf.adminName = name1;
            sql = "SELECT * FROM customs WHERE customerName = '" + name1 + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next() && name1.length() > 4) {
                    Conf.id = resultSet.getInt("customerID");
                    String passWord1 = new String(customerPassword.getPassword());
                    sql = "SELECT * FROM customs WHERE customerName = '" + name1 + "' AND customerPassword = '" + passWord1 + "'";
                    resultSet = dataBase.statement.executeQuery(sql);
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "登录成功！");
                        this.dispose();
                        new CustomerOperation();
                    } else {
                        JOptionPane.showMessageDialog(this, "密码错误！");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "用户不存在！");
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==But2){
            this.dispose();
            new PasswordReset(Conf.id);
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
