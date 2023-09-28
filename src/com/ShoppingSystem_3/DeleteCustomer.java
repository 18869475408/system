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
public class DeleteCustomer extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    private JLabel lbGood = new JLabel("请输入你要删除的客户名:");
    private JTextField goodsName = new JTextField(20);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public  DeleteCustomer(){
        super("客户删除~~~~");
        this.setLayout(new FlowLayout());
        this.add(lbGood);
        this.add(goodsName);
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
            JOptionPane.showMessageDialog(this,"你现在正在执行客户删除操作，是否继续？");
            String delGoods = new String(goodsName.getText());
            sql = "SELECT * FROM customs WHERE customerName = '" + delGoods + "'";
            try {
                resultSet = dataBase.statement.executeQuery(sql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                if (resultSet.next()) {
                    sql = "DELETE FROM customs WHERE customerName = '"+delGoods+"'";
                    JOptionPane.showMessageDialog(this,"删除成功！");
                    dataBase.statement.executeUpdate(sql);
                    this.dispose();
                    new CustomerMangement();
                }
                else{
                    JOptionPane.showMessageDialog(this,"查询不到该用户的相关信息!,请重新输入！");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==But2){
            JOptionPane.showMessageDialog(this, "放弃删除~~~");
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
