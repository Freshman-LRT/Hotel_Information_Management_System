package com.hotel0.view;

import com.hotel0.util.DBUtil;

/**
 * 程序入口
 */
public class HotelMain {
    public static void main(String[] args) {
        // 连接数据库
        DBUtil name =new DBUtil("root","LIU1840135132","demo");
        LoginView lg = new LoginView();
    }
}
