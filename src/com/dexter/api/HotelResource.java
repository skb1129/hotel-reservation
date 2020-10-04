package com.dexter.api;

import com.dexter.model.Customer;
import com.dexter.model.IRoom;
import com.dexter.model.Reservation;
import com.dexter.service.CustomerService;
import com.dexter.service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource = null;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (IllegalArgumentException e) {
            System.out.printf("Invalid email: %s%n", e.getMessage());
        }
    }

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (null == customer) return null;
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (null == customer) return null;
        return reservationService.getCustomersReservations(customer);
    }
}
