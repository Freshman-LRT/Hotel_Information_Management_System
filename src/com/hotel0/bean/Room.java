package com.hotel0.bean;

public class Room {
    private String roomId;
    private String hotelId;
    private String roomType;
    private double price;
    private String status;
    private int capacity;

    public Room(String roomId, String hotelId, String roomType, double price, String status, int capacity) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.price = price;
        this.status = status;
        this.capacity = capacity;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", hotelId='" + hotelId + '\'' +
                ", roomType='" + roomType + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}