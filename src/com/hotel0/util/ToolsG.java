package com.hotel0.util;

import javax.swing.*;
import java.awt.*;
//窗口居中
public class ToolsG {
    public static void setPos(JFrame jFrame, int width, int height) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = (int) screenSize.getWidth() /2 - width / 2;
        int y = (int) screenSize.getHeight() / 2 - height / 2;
        jFrame.setBounds(x, y, width, height);

    }

    /**
     * 弹窗
     * @param message
     */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "提示信息", JOptionPane.WARNING_MESSAGE);
        //如果密码为空，提示错误
    }
}
