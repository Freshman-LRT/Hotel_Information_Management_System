package com.hotel0.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBmySQL {

    /**
     * 封装了一个查询工具
     * @param sql
     * @param data
     * @return
     * @throws SQLException
     */
    public static ResultSet query(String sql, String ...data) throws SQLException {
        Connection con = DBUtil.getConnection();
        if (con == null) {
            throw new SQLException("数据库连接未初始化");
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i + 1, data[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("查询失败: " + e.getMessage());
        }
    }

    /**
     * 封装更新操作，返回受影响的行数
     * @param sql
     * @param data
     * @return
     * @throws SQLException
     */
    public static int update(String sql, String ...data) throws SQLException {
        Connection con = DBUtil.getConnection();
        if (con == null) {
            throw new SQLException("数据库连接未初始化");
        }

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < data.length; i++) {
                ps.setString(i + 1, data[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("更新失败: " + e.getMessage());
        }
    }

    public static String queryForString(String sql) throws SQLException {
        Connection con = DBUtil.getConnection();
        if (con == null) {
            throw new SQLException("数据库连接未初始化");
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }
}
