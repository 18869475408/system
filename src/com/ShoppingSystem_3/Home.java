package com.ShoppingSystem_3;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class Home extends JFrame implements ActionListener {
    private JLabel customer = new JLabel("\t1--客户(customer)\n ");
    private JLabel admin = new JLabel("\t2--管理员(administrator)\n");
    private JLabel choose = new JLabel("\t请选择您的身份：\n");
    private JButton But1 = new JButton("1");
    private JButton But2 = new JButton("2");

    public  Home(){
        super("欢迎~~~");
        this.setLayout(new FlowLayout());
        this.add(customer);
        this.add(admin);
        this.add(choose);
        this.add(But1);
        this.add(But2);
        this.setSize(300,180);
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
            this.dispose();
           new CustomerHome();
        }
        if(e.getSource()==But2){
            this.dispose();
            new AdminLogin();
        }
    }

    public static void toCenter(Component comp){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
        comp.setLocation(((int)rec.getWidth()-comp.getWidth()/2),((int)rec.getHeight()-comp.getHeight()-comp.getHeight()/2));
    }
}
