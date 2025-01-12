package com.hotel0.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 负责数据库连接
 */
public class DBUtil {
    private String account;
    private String password;
    private String name;
    private static Connection con = null;

    public DBUtil(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
        init();
    }

    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (Exception e) {
            System.out.println("数据库驱动加载失败: " + e.getMessage());
            return;
        }

        try {
            String url = "jdbc:mysql://localhost:3306/" + name + "?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            con = DriverManager.getConnection(url, account, password);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            System.out.println("数据库连接失败: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return con;
    }
}