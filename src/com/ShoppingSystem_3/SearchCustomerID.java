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
public class SearchCustomerID extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbGood = new JLabel("请输入客户ID:");
    private JTextField customerID = new JTextField(5);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  SearchCustomerID(){
        super("客户ID搜索~~~~");
        this.setLayout(new FlowLayout());
        this.add(lbGood);
        this.add(customerID);
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
            if(customerID.getText().trim().length()!=0){
            int inputs2 = Integer.parseInt(customerID.getText());
            sql = "SELECT customerID FROM customs WHERE customerID = "+inputs2+"";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
                try {
                    if (resultSet.next()) {
                        sql = "SELECT * FROM customs WHERE customerID = " + inputs2 + "";
                        resultSet = dataBase.statement.executeQuery(sql);
                        System.out.println("客户ID\t用户名\t用户级别\t\t用户注册时间\t\t\t消费总金额\t\t\t用户邮箱\t\t\t用户手机号\t");
                        while (true) {
                            try {
                                if (!resultSet.next()) break;
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            } // 让光标向后移动，如果没有更多行，则返回 false
                            String name = null;
                            try {
                                name = resultSet.getString("customerName");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            String grade = resultSet.getString("customerGrade");
                            String time = resultSet.getString("registrationTime");
                            double cost = resultSet.getDouble("costTotal");
                            String email = resultSet.getString("customerEmail");
                            String phone = resultSet.getString("customerPhoneNumber");
                            JOptionPane.showMessageDialog(this, "搜索结果如下~~~");
                            this.dispose();
                            new CustomerMangement();
                            System.out.println("\t" + inputs2 + "\t" + name + "\t" + grade + "\t" + time + "\t" + cost + "\t\t\t" + email + "\t" + phone + "\t");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "用户ID输入错误或用户不存在！");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                JOptionPane.showMessageDialog(this, "输入为空！");
                }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃搜索~~~");
            this.dispose();
            new CustomerMangement();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}

