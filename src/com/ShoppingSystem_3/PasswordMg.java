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
public class PasswordMg extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbName = new JLabel("请输入需要修改密码用户的用户名：:");
    private JTextField customerName = new JTextField(20);
    private JLabel lbPassword = new JLabel("请输入修改后的密码：");
    private JPasswordField customerPassword = new JPasswordField(20);
    private JLabel lbPassword1 = new JLabel("请再次输入密码确认：");
    private JPasswordField customerPassword1 = new JPasswordField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  PasswordMg(){
        super("用户密码管理");
        this.setLayout(new FlowLayout());
        this.add(lbName);
        this.add(customerName);
        this.add(lbPassword);
        this.add(customerPassword);
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
        Font f = new Font("微软雅黑", Font.BOLD, 25);
        lbName.setFont(f);
        lbName.setForeground(Color.BLACK);

        lbPassword.setBounds(180, 45, 450, 100);
        Font f1 = new Font("微软雅黑", Font.BOLD, 25);
        lbPassword.setFont(f1);

        lbPassword1.setBounds(180, 45, 450, 100);
        Font f2 = new Font("微软雅黑", Font.BOLD, 25);
        lbPassword1.setFont(f2);

        But1.setBounds(180, 45, 200, 50);
        Font f4 = new Font("微软雅黑", Font.BOLD, 25);
        But1.setFont(f4);
        But1.setForeground(Color.BLACK);

        But2.setBounds(180, 45, 200, 50);
        Font f5 = new Font("微软雅黑", Font.BOLD, 25);
        But2.setFont(f4);
        But2.setForeground(Color.BLACK);

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==But1) {
            boolean flag = true;
            String name = lbName.getText();
            sql = "SELECT * FROM customs WHERE customerName = '" + name + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "用户名输入错误或用户不存在，请检查后再次输入:");
                    flag = false;
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            String cusPassword1 = new String(customerPassword.getPassword());
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
            if (flag == true && (cusPassword1.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    (cusPassword2.matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,}$")) &&
                    cusPassword1.equals(cusPassword2)) {
                sql = "UPDATE customs SET customerPassword = '" + cusPassword1 + "' WHERE customerName = '" + name + "'";
                try {
                    dataBase.statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(this, "密码修改成功~~~~");
            }
        }
        if(e.getSource()==But2){
            new CustomerHome();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
