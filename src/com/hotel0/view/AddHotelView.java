package com.hotel0.view;

import com.hotel0.bean.Hotel;
import com.hotel0.dao.HotelDao;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHotelView extends JFrame {

    private JTextField hotelNameField;
    private JTextField hotelAddressField;
    private JTextField hotelPhoneField;
    private JTextField hotelRatingField;

    public AddHotelView() {
        initialize();
    }

    private void initialize() {
        setTitle("录入酒店信息");
        setSize(400, 300);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时只关闭当前窗口

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("酒店信息录入"));
        getContentPane().add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间距

        // 酒店名称
        JLabel hotelNameLabel = new JLabel("酒店名称:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(hotelNameLabel, gbc);

        hotelNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(hotelNameField, gbc);

        // 酒店地址
        JLabel hotelAddressLabel = new JLabel("酒店地址:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(hotelAddressLabel, gbc);

        hotelAddressField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(hotelAddressField, gbc);

        // 酒店联系电话
        JLabel hotelPhoneLabel = new JLabel("联系电话:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(hotelPhoneLabel, gbc);

        hotelPhoneField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(hotelPhoneField, gbc);

        // 酒店评分
        JLabel hotelRatingLabel = new JLabel("酒店评分:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(hotelRatingLabel, gbc);

        hotelRatingField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(hotelRatingField, gbc);

        // 提交按钮
        JButton submitButton = new JButton("提交");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        // 提交按钮事件
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取输入的数据
                String hotelName = hotelNameField.getText();
                String hotelAddress = hotelAddressField.getText();
                String hotelPhone = hotelPhoneField.getText();
                String hotelRating = hotelRatingField.getText();

                // 创建 Hotel 对象
                Hotel hotel = new Hotel();
                hotel.setHotelId(HotelDao.generateHotelId()); // 生成唯一 ID
                hotel.setHotelName(hotelName);
                hotel.setHotelAddress(hotelAddress);
                hotel.setHotelTel(hotelPhone);
                hotel.setHotelRating(hotelRating);

                // 调用 HotelDao 插入数据
                boolean isSuccess = HotelDao.insertHotel(hotel);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(AddHotelView.this, "酒店信息录入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // 关闭当前窗口
                } else {
                    JOptionPane.showMessageDialog(AddHotelView.this, "酒店信息录入失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}