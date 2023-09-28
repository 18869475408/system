package com.ShoppingSystem_3;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 /**
 * @author 陈俊睿
 * @version 3.0
 */
@SuppressWarnings({"all"})
public class SendPassword extends Component {
    DataBase dataBase = new DataBase();
    int a = dataBase.connectDataBase();
    String sql = "";
    ResultSet resultSet  ;
    public void SendPasswordToCustomer () throws SQLException {
        // 收件人电子邮箱
        sql = "SELECT * FROM customs WHERE customerID = '" + Conf.id + "'";
        try {
            resultSet = dataBase.statement.executeQuery(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
            String customerEmail = null;
            if (resultSet.next()) {
                customerEmail = resultSet.getString("customerEmail");
            }
                String to = customerEmail;

        // 发件人电子邮箱
        String from = "1726761496@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("1726761496@qq.com", "uxezpubhfwmlebbe"); //发件人邮件用户名、授权码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("【腾讯科技】重置密码");

            // 设置消息体
            message.setText(" Zqw123456789@ 用于身份验证，请勿泄露和转发。如非本人操作，请忽略此短信。");

            // 发送消息
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }

        sql = "UPDATE customs SET customerPassword = 'Zqw123456789@' WHERE customerID = " +Conf.id + "";
        try {
            dataBase.statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}