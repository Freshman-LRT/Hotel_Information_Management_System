package com.hotel0.view;

import com.hotel0.bean.Book;
import com.hotel0.dao.BookDao;
import com.hotel0.dao.RoomDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class BookRoomView extends JFrame {

    private JTextField userIdField;
    private JTextField hotelIdField;
    private JTextField roomIdField;
    private JTextField checkInDateField;
    private JTextField checkOutDateField;

    public BookRoomView() {
        initialize();
    }

    private void initialize() {
        setTitle("预订房间");
        setSize(400, 300);
        setLocationRelativeTo(null); // 居中显示
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时只关闭当前窗口

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel);

        panel.add(new JLabel("用户ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        panel.add(new JLabel("酒店ID:"));
        hotelIdField = new JTextField();
        panel.add(hotelIdField);

        panel.add(new JLabel("房间ID:"));
        roomIdField = new JTextField();
        panel.add(roomIdField);

        panel.add(new JLabel("入住日期 (yyyy-MM-dd):"));
        checkInDateField = new JTextField();
        panel.add(checkInDateField);

        panel.add(new JLabel("退房日期 (yyyy-MM-dd):"));
        checkOutDateField = new JTextField();
        panel.add(checkOutDateField);

        JButton submitButton = new JButton("提交");
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String hotelId = hotelIdField.getText();
                String roomId = roomIdField.getText();
                Date checkInDate = java.sql.Date.valueOf(checkInDateField.getText());
                Date checkOutDate = java.sql.Date.valueOf(checkOutDateField.getText());

                // 检查房间状态
                if (RoomDao.updateRoomStatus(roomId, "已预订")) {
                    Book book = new Book(BookDao.generateBookId(), userId, hotelId, roomId, checkInDate, checkOutDate);
                    boolean isSuccess = BookDao.insertBook(book);
                    if (isSuccess) {
                        JOptionPane.showMessageDialog(BookRoomView.this, "房间预订成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // 关闭当前窗口
                    } else {
                        JOptionPane.showMessageDialog(BookRoomView.this, "房间预订失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(BookRoomView.this, "房间状态更新失败！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
