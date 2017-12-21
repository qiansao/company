package com.cp.frame;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JLabel minLabel;
    private JLabel maxLabel;
    private JLabel closeLabel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private Color lightWhite;

    public LoginFrame(){
    setUndecorated(true);
    setTitle("登录界面");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    mainPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/bg.jpg"));
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(LoginFrame.this.getWidth(), LoginFrame.this.getHeight(), Image.SCALE_FAST));
            imageIcon.paintIcon(this, g, 0, 0);
        }
    };
    mainPanel.setLayout(new BorderLayout(500,220));
    mainPanel.add(topPanel,BorderLayout.NORTH);
lightWhite=new Color(204,204,204,120);
    centerPanel.setBackground(lightWhite);
    mainPanel.add(centerPanel,BorderLayout.CENTER);
    mainPanel.add(westPanel,BorderLayout.WEST);
    mainPanel.add(eastPanel,BorderLayout.EAST);
    mainPanel.add(southPanel,BorderLayout.SOUTH);
    add(mainPanel);
    setVisible(true);
    closeLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int result = JOptionPane.showConfirmDialog(null, "是否退出", "标题", 0);
            if (result == 0) {
                LoginFrame.this.dispose();
            }
        }
    });
}
    public static void main(String[] args) throws Exception {
        //org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        UIManager.put("RootPane.setupButtonVisible", false);
        new LoginFrame();
    }
}

