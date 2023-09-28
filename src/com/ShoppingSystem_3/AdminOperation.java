package com.ShoppingSystem_3;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

/**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class AdminOperation  extends JFrame implements ActionListener {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet ;
    private JLabel choose1 = new JLabel("  1--修改管理员密码       ");
    private JLabel choose2 = new JLabel("  2--修改客户密码         ");
    private JLabel choose3 = new JLabel("  3--客户管理            ");
    private JLabel choose4 = new JLabel("  4-商品管理             ");
    private JLabel choose5 = new JLabel("  5--退出登录            ");
    private JLabel choose = new JLabel("  请选择您接下来的操作：");
    private JTextField cho = new JTextField(10);
    private JButton But1 = new JButton("确定");
    private JButton But2 = new JButton("退出");

    public AdminOperation (){
        super("欢迎您来到管理员操作界面~~~~");
        this.setLayout(new FlowLayout());
        this.add(choose1);
        this.add(choose2);
        this.add(choose3);
        this.add(choose4);
        this.add(choose5);
        this.add(choose);
        this.add(cho);
        this.add(But1);
        this.add(But2);
        this.setSize(400,300);
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
                if (choose > 0 && choose < 6) {
                    switch (choose) {
                        case 1:
                            this.dispose();
                            new AdminModifyOwnPassword();
                            break;
                        case 2:
                            this.dispose();
                            new AdminModifyCustomerPassword();
                            break;
                        case 3:
                            this.dispose();
                            new CustomerMangement();
                            break;
                        case 4:
                            this.dispose();
                            new GoodsManagement();
                            break;
                        case 5:
                            this.dispose();
                            new Home();
                            break;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请重新输入~~~");
                }
            }
                else{
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
