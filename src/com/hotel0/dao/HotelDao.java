package com.hotel0.dao;

import com.hotel0.bean.Hotel;
import com.hotel0.util.DBmySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class HotelDao {

    /**
     * 插入酒店信息
     * @param hotel
     * @return 是否插入成功
     */
    public static boolean insertHotel(Hotel hotel) {
        String sql = "INSERT INTO hotels (hotel_id, hotel_name, hotel_address, hotel_tel, hotel_rating) VALUES (?, ?, ?, ?, ?)";
        try {
            int rows = DBmySQL.update(sql, hotel.getHotelId(), hotel.getHotelName(), hotel.getHotelAddress(), hotel.getHotelTel(), hotel.getHotelRating());
            return rows > 0; // 插入成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成从0001开始的唯一ID
     * @return
     */
    public static String generateHotelId() {
        /**
         * 获取当前最大ID，实现每注册一个人，生成一个唯一ID
         */
        String maxId = getMaxHotelIdFromDB();

        if (maxId == null || maxId.isEmpty()) {
            return "0001";
        }

        int nextId = Integer.parseInt(maxId) + 1;

        return String.format("%04d", nextId);
    }

    private static String getMaxHotelIdFromDB() {
        String sql = "SELECT MAX(hotel_id) FROM hotels";
        try {
            String maxId = DBmySQL.queryForString(sql);
            //如果maxId为空，返回 0000
            return (maxId == null || maxId.isEmpty()) ? "0000" : maxId;
        } catch (SQLException e) {
            e.printStackTrace();
            return "0000";  //异常
        }
    }

    public static boolean updateHotel(Hotel hotel) {
        String sql = "UPDATE hotels SET hotel_name=?, hotel_address=?, hotel_tel=?, hotel_rating=? WHERE hotel_id=?";
        try {
            int rows = DBmySQL.update(sql, hotel.getHotelName(), hotel.getHotelAddress(), hotel.getHotelTel(), hotel.getHotelRating(), hotel.getHotelId());
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Hotel> searchHotels(String keyword) {
        String sql = "SELECT * FROM hotels WHERE hotel_id LIKE ? OR hotel_name LIKE ?";
        List<Hotel> hotels = new ArrayList<>();
        try {
            ResultSet res = DBmySQL.query(sql, "%" + keyword + "%", "%" + keyword + "%");
            while (res.next()) {
                Hotel hotel = new Hotel(
                        res.getString("hotel_id"),
                        res.getString("hotel_name"),
                        res.getString("hotel_address"),
                        res.getString("hotel_tel"),
                        res.getString("hotel_rating")
                );
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public static Hotel getHotelById(String hotelId) {
        String sql = "SELECT * FROM hotels WHERE hotel_id = ?";
        try {
            ResultSet res = DBmySQL.query(sql, hotelId);
            if (res.next()) {
                return new Hotel(
                        res.getString("hotel_id"),
                        res.getString("hotel_name"),
                        res.getString("hotel_address"),
                        res.getString("hotel_tel"),
                        res.getString("hotel_rating")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据酒店ID获取房间数
     * @param hotelId 酒店ID
     * @return
     */
    public static int getRoomCountByHotelId(String hotelId) {
        String sql = "SELECT room_count FROM hotels WHERE hotel_id = ?";
        try {
            ResultSet res = DBmySQL.query(sql, hotelId);
            if (res.next()) {
                return res.getInt("room_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 查询失败
    }
}