package com.hotel0.dao;

import com.hotel0.bean.Book;
import com.hotel0.util.DBmySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class BookDao {

    /**
     * 插入预订信息
     * @param book
     * @return 是否插入成功
     */
    public static boolean insertBook(Book book) {
        String sql = "INSERT INTO books (book_id, user_id, hotel_id, room_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // 将 Date 转换为 String
            String checkInDateStr = new java.sql.Date(book.getCheckInDate().getTime()).toString();
            String checkOutDateStr = new java.sql.Date(book.getCheckOutDate().getTime()).toString();

            int rows = DBmySQL.update(sql, book.getBookId(), book.getUserId(), book.getHotelId(), book.getRoomId(), checkInDateStr, checkOutDateStr);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户ID查询预订信息
     * @param userId
     * @return 预订列表
     */
    public static List<Book> getBooksByUserId(String userId) {
        String sql = "SELECT * FROM books WHERE user_id = ?";
        List<Book> books = new ArrayList<>();
        try {
            ResultSet res = DBmySQL.query(sql, userId);
            while (res.next()) {
                Book book = new Book(
                        res.getString("book_id"),
                        res.getString("user_id"),
                        res.getString("hotel_id"),
                        res.getString("room_id"),
                        res.getDate("check_in_date"),
                        res.getDate("check_out_date")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * 取消预订
     * @param bookId
     * @return 是否取消成功
     */
    public static boolean cancelBook(String bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";
        try {
            int rows = DBmySQL.update(sql, bookId);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成唯一的预订ID
     * @return 生成的唯一ID
     */
    public static String generateBookId() {
        String sql = "SELECT MAX(book_id) FROM books";
        try {
            String maxId = DBmySQL.queryForString(sql);
            if (maxId == null || maxId.isEmpty()) {
                return "0001";
            }
            int nextId = Integer.parseInt(maxId) + 1;
            return String.format("%04d", nextId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "0001";
        }
    }
}