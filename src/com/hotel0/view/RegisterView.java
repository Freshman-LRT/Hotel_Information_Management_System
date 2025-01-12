package com.hotel0.view;

import com.hotel0.util.ToolsG;
import com.hotel0.dao.AdminDao;
import com.hotel0.bean.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterView {

	public JFrame frame;
	private JTextField textField; // 账号
	private JTextField textField_1; // 姓名
	private JTextField textField_2; // 设置密码
	private JTextField textField_3; // 确认密码
	private JTextField textField_4; // 电话

	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
	}

	private final int width = 400;
	private final int height = 400;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("注册界面");
		ToolsG.setPos(frame, width, height);
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.getContentPane().setBackground(new Color(240, 240, 240)); // 浅灰色背景

		JLabel lblNewLabel_1 = new JLabel("酒店信息管理系统注册");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("SimHei", Font.BOLD, 28));
		lblNewLabel_1.setForeground(new Color(0, 122, 204, 255)); // 蓝色字体

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 20, 0); // 上下间距
		frame.getContentPane().add(lblNewLabel_1, gbc);

		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(240, 240, 240)); // 浅灰色背景

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setFont(new Font("SimHei", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_2 = new JLabel("姓名：");
		lblNewLabel_2.setFont(new Font("SimHei", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_3 = new JLabel("设置密码：");
		lblNewLabel_3.setFont(new Font("SimHei", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_4 = new JLabel("确认密码：");
		lblNewLabel_4.setFont(new Font("SimHei", Font.PLAIN, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_5 = new JLabel("电话：");
		lblNewLabel_5.setFont(new Font("SimHei", Font.PLAIN, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		textField = new JTextField(15); // 账号
		textField.setFont(new Font("SimHei", Font.PLAIN, 16));

		textField_1 = new JTextField(15); // 姓名
		textField_1.setFont(new Font("SimHei", Font.PLAIN, 16));

		textField_2 = new JTextField(15); // 设置密码
		textField_2.setFont(new Font("SimHei", Font.PLAIN, 16));

		textField_3 = new JTextField(15); // 确认密码
		textField_3.setFont(new Font("SimHei", Font.PLAIN, 16));

		textField_4 = new JTextField(15); // 电话
		textField_4.setFont(new Font("SimHei", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("注册");
		btnNewButton.setFont(new Font("SimHei", Font.BOLD, 16));
		btnNewButton.setBackground(new Color(0, 102, 204)); // 蓝色背景
		btnNewButton.setForeground(Color.WHITE); // 白色字体
		btnNewButton.setFocusPainted(false); // 去掉焦点边框
		btnNewButton.setPreferredSize(new Dimension(100, 40)); // 设置按钮大小

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 0, 5, 0); // 上下间距
		panel.add(lblNewLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(textField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(lblNewLabel_2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(textField_1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblNewLabel_3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(textField_2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblNewLabel_4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(textField_3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblNewLabel_5, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(textField_4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 0, 0, 0); // 上间距
		panel.add(btnNewButton, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.getContentPane().add(panel, gbc);

		// 注册按钮点击事件
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user_id = textField.getText(); // 账号
				String user_name = textField_1.getText(); // 姓名
				String user_password = textField_2.getText(); // 设置密码
				String confirm_password = textField_3.getText(); // 确认密码
				String user_tel = textField_4.getText(); // 电话

				// 字段非空验证
				if (user_id.isEmpty()) {
					ToolsG.showMessage("请输入账号");
				} else if (user_name.isEmpty()) {
					ToolsG.showMessage("请输入姓名");
				} else if (user_password.isEmpty()) {
					ToolsG.showMessage("请输入密码");
				} else if (confirm_password.isEmpty()) {
					ToolsG.showMessage("请确认密码");
				} else if (user_tel.isEmpty()) {
					ToolsG.showMessage("请输入电话");
				} else if (!user_password.equals(confirm_password)) {
					ToolsG.showMessage("两次输入的密码不一致");
				} else if (!user_tel.matches("\\d{11}")) { // 电话格式验证
					ToolsG.showMessage("电话号码格式不正确");
				} else {
					// 注册逻辑
					Admin admin = new Admin(user_id, user_name, user_password, user_tel);
					try {
						boolean isSuccess = AdminDao.register(admin);
						if (isSuccess) {
							ToolsG.showMessage("注册成功！");
							frame.dispose(); // 关闭注册窗口
						} else {
							ToolsG.showMessage("注册失败，请稍后重试！");
						}
					} catch (Exception ex) {
						ex.printStackTrace();
						ToolsG.showMessage("注册失败，系统错误！");
					}
				}

			}
		});
	}
}
