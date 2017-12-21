package com.cp.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 刘宇欣 12.19
 */
public class Login extends JFrame{
    private JPanel jp1,jp2,jp3;
    private JButton jbtnLogin;
    private JLabel jlbUser,jlbPasswd;
    private JPasswordField jpfPasswd;
    private JTextField jtfUser;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Login();
    }

    public Login()
    {
        jp1 = new JPanel(new BorderLayout());
        jp2 = new JPanel(new GridLayout(2,2));
        jp3 = new JPanel();
        jbtnLogin = new JButton("登录");
        jlbUser = new JLabel("用户名：");
        jlbPasswd = new JLabel("密码：");
        jtfUser = new JTextField();
        jpfPasswd = new JPasswordField();
        jp3.add(jbtnLogin);
        jp2.add(jlbUser);
        jp2.add(jtfUser);
        jp2.add(jlbPasswd);
        jp2.add(jpfPasswd);
        jp1.add(jp2);
        jp1.add(jp3,"South");
        this.add(jp1);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(width/2-200, height/2-150);
        this.setSize(400, 150);
    }

}
