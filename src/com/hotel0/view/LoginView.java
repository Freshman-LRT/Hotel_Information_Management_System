package com.hotel0.view;
import com.hotel0.bean.Admin;
import com.hotel0.dao.AdminDao;
import com.hotel0.util.ToolsG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView {
    public static String currentUserId = null;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private final int WIDTH = 500;
    private final int HEIGHT = 300;
    public static String user_role = "0"; // 用户

    public LoginView() {
        frame = new JFrame();
        frame.setTitle("酒店信息管理系统");

        initView();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ToolsG.setPos(frame, WIDTH, HEIGHT);
        frame.validate();
    }

    public void initView() {
        // 创建主面板
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        panel.setBackground(new Color(240, 240, 240)); // 浅灰色背景
        frame.add(panel);

        // 创建标题标签
        JLabel titleLabel = new JLabel("HEU酒店管理系统");
        titleLabel.setFont(new Font("SimHei", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 204)); // 蓝色字体

        // 调整位置
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0); // 上下间距
        panel.add(titleLabel, gbc);

        // 创建登录面板
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(240, 240, 240)); // 浅灰色背景

        // 用户名标签和输入框
        JLabel userLabel = new JLabel("账号：");
        userLabel.setFont(new Font("SimHei", Font.PLAIN, 16));
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("SimHei", Font.PLAIN, 16));

        // 密码标签和输入框
        JLabel passLabel = new JLabel("密码：");
        passLabel.setFont(new Font("SimHei", Font.PLAIN, 16));
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("SimHei", Font.PLAIN, 16));
        passwordField.setEchoChar((char) 0); // 设置 echoChar 为 0，使密码可见

        // 登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("SimHei", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 102, 204)); // 蓝色背景
        loginButton.setForeground(Color.WHITE); // 白色字体
        loginButton.setFocusPainted(false); // 去掉焦点边框
        loginButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小

        // 设置监听器，触发事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 判断输入框是否有内容
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (username.equals("")) {
                    ToolsG.showMessage("请输入账号");
                } else if (password.equals("")) {
                    ToolsG.showMessage("请输入密码");
                } else {
                    // 查询数据库，验证账号和密码
                    Admin admin = AdminDao.isLogin(username, password);
                    if (admin == null) {
                        ToolsG.showMessage("账号或密码错误");
                    } else {
//                        // 登录成功，设置权限
//                        LoginView.user_role = admin.getUser_role(); // 给权限值
//                        ToolsG.showMessage("登录成功");
                        currentUserId = admin.getUser_id();
                        // 关闭当前登录窗口
                        frame.dispose();

                        // 打开 ManageView 窗口
                        ManageView manageView = new ManageView();
                        manageView.frame.setVisible(true);
                    }
                }
            }
        });

        // 注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.setFont(new Font("SimHei", Font.PLAIN, 14));
        registerButton.setForeground(new Color(0, 102, 204));
        registerButton.setBackground(new Color(240, 240, 240));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 鼠标悬停时变为手型


        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterView window = new RegisterView();// 进入注册界面
                window.frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        // 添加组件到登录面板
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0); // 上下间距
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 0, 0); // 上间距
        loginPanel.add(loginButton, gbc);

        // 添加登录面板到主面板
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(loginPanel, gbc);

        // 添加注册按钮到主面板（左下角）
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(new Color(240, 240, 240)); // 浅灰色背景
        bottomPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTHWEST; // 左下角对齐
        gbc.insets = new Insets(0, 70, 10, 0); // 左边距和底边距
        panel.add(bottomPanel, gbc);
    }
}