package com.hotel0.dao;

import com.hotel0.bean.Admin;
import com.hotel0.util.DBmySQL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    /**
     * 是否登录
     * @param username
     * @param password
     * @return
     */
    public static Admin isLogin(String username, String password) {
        String sql = "select * from users where user_id=? and user_password=?";
        try {
            ResultSet res = DBmySQL.query(sql, username, password);
            if (res.next()) {
                Admin admin = new Admin(
                        res.getString("user_id"),
                        res.getString("user_name"),
                        res.getString("user_password"),
                        res.getString("user_tel")
                );
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(Admin admin) throws SQLException {
        String sql = "INSERT INTO users (user_id, user_name, user_password, user_tel) VALUES (?, ?, ?, ?)";
        try {
            // 检查账号是否已存在
            String checkSql = "SELECT * FROM users WHERE user_id = ?";
            ResultSet res = DBmySQL.query(checkSql, admin.getUser_id());
            if (res.next()) {
                return false; // 账号已存在
            }

            // 插入新用户
            int rows = DBmySQL.update(sql, admin.getUser_id(), admin.getUser_name(), admin.getUser_password(), admin.getUser_tel());
            return rows > 0; // 插入成功
        } catch (SQLException e) {
            throw e; // 抛出异常
        }
    }

    public static Admin getUserById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            ResultSet res = DBmySQL.query(sql, userId);
            if (res.next()) {
                return new Admin(
                        res.getString("user_id"),
                        res.getString("user_name"),
                        res.getString("user_password"),
                        res.getString("user_tel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
