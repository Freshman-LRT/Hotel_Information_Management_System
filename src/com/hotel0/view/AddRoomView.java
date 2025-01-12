package com.hotel0.view;

import com.hotel0.bean.Room;
import com.hotel0.dao.RoomDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomView extends JFrame {

    private JTextField roomIdField;
    private JTextField hotelIdField;
    private JTextField roomTypeField;
    private JTextField priceField;
    private JTextField statusField;
    private JTextField capacityField;

    public AddRoomView() {
        initialize();
    }

    private void initialize() {
        setTitle("录入房间信息");
        setSize(400, 300);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时只关闭当前窗口

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel);

        panel.add(new JLabel("房间ID:"));
        roomIdField = new JTextField();
        panel.add(roomIdField);

        panel.add(new JLabel("酒店ID:"));
        hotelIdField = new JTextField();
        panel.add(hotelIdField);

        panel.add(new JLabel("房间类型:"));
        roomTypeField = new JTextField();
        panel.add(roomTypeField);

        panel.add(new JLabel("价格:"));
        priceField = new JTextField();
        panel.add(priceField);

        panel.add(new JLabel("当前状态:"));
        statusField = new JTextField();
        panel.add(statusField);

        panel.add(new JLabel("可住人数:"));
        capacityField = new JTextField();
        panel.add(capacityField);

        JButton submitButton = new JButton("提交");
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomId = roomIdField.getText();
                String hotelId = hotelIdField.getText();
                String roomType = roomTypeField.getText();
                double price = Double.parseDouble(priceField.getText());
                String status = statusField.getText();
                int capacity = Integer.parseInt(capacityField.getText());

                Room room = new Room(roomId, hotelId, roomType, price, status, capacity);
                boolean isSuccess = RoomDao.insertRoom(room);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(AddRoomView.this, "房间信息录入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // 关闭当前窗口
                } else {
                    JOptionPane.showMessageDialog(AddRoomView.this, "房间信息录入失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
