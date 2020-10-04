package com.dexter.service;

import com.dexter.model.Customer;
import com.dexter.model.IRoom;
import com.dexter.model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private static ReservationService reservationService = null;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    Collection<IRoom> rooms = new HashSet<>();
    Collection<Reservation> reservations = new HashSet<>();

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String roomNumber) {
        Optional<IRoom> room = rooms.stream().filter(r -> roomNumber.equals(r.getRoomNumber())).findFirst();
        return room.orElse(null);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<Reservation> getCustomersReservations(Customer customer) {
        return reservations.stream().filter(r -> customer.equals(r.getCustomer())).collect(Collectors.toCollection(HashSet::new));
    }

    public void printAllReservations() {
        reservations.forEach(System.out::println);
    }
}
