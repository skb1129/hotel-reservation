package com.dexter.model;

import java.util.Objects;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Boolean isFree() {
        return price == 0;
    }

    @Override
    public String toString() {
        return String.format("Room\nroomNumber=%s\nprice=%s\nroomType=%s\n", roomNumber, price, roomType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return roomNumber != null ? roomNumber.hashCode() : 0;
    }
}
