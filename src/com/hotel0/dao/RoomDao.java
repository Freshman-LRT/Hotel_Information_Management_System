package com.hotel0.dao;

import com.hotel0.bean.Room;
import com.hotel0.util.DBmySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    /**
     * 插入房间
     * @param
     * @return 是否插入成功
     */
    public static boolean insertRoom(Room room) {
        String sql = "INSERT INTO rooms (room_id, hotel_id, room_type, price, status, capacity) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            // 强制转换为转换为 String
            String priceStr = String.valueOf(room.getPrice());
            String capacityStr = String.valueOf(room.getCapacity());

            int rows = DBmySQL.update(sql, room.getRoomId(), room.getHotelId(), room.getRoomType(), priceStr, room.getStatus(), capacityStr);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据酒店ID查询房间
     * @param hotelId
     * @return 房间列表
     */
    public static List<Room> getRoomsByHotelId(String hotelId) {
        String sql = "SELECT * FROM rooms WHERE hotel_id = ?";
        List<Room> rooms = new ArrayList<>();
        try {
            ResultSet res = DBmySQL.query(sql, hotelId);
            while (res.next()) {
                Room room = new Room(
                        res.getString("room_id"),
                        res.getString("hotel_id"),
                        res.getString("room_type"),
                        res.getDouble("price"),
                        res.getString("status"),
                        res.getInt("capacity")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    /**
     * 更新房间状态
     * @param roomId
     * @param status
     * @return 是否更新成功
     */
    public static boolean updateRoomStatus(String roomId, String status) {
        String sql = "UPDATE rooms SET status = ? WHERE room_id = ?";
        try {
            int rows = DBmySQL.update(sql, status, roomId);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除房间
     * @param roomId
     * @return 是否删除成功
     */
    public static boolean deleteRoom(String roomId) {
        String sql = "DELETE FROM rooms WHERE room_id = ?";
        try {
            int rows = DBmySQL.update(sql, roomId);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET hotel_id=?, room_type=?, price=?, status=?, capacity=? WHERE room_id=?";
        try {
            // 将 double 和 int 转换为 String
            String priceStr = String.valueOf(room.getPrice());
            String capacityStr = String.valueOf(room.getCapacity());

            int rows = DBmySQL.update(sql,
                    room.getHotelId(),
                    room.getRoomType(),
                    priceStr,
                    room.getStatus(),
                    capacityStr,
                    room.getRoomId()
            );
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Room getRoomById(String roomId) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        try {
            ResultSet res = DBmySQL.query(sql, roomId);
            if (res.next()) {
                return new Room(
                        res.getString("room_id"),
                        res.getString("hotel_id"),
                        res.getString("room_type"),
                        res.getDouble("price"),
                        res.getString("status"),
                        res.getInt("capacity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
