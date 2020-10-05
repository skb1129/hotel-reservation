package com.dexter;

import com.dexter.api.HotelResource;
import com.dexter.model.IRoom;
import com.dexter.model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    Scanner scanner;
    HotelResource hotelResource = HotelResource.getInstance();

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void reserveRoom() throws ParseException {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.nextLine();
        IRoom room = hotelResource.getRoom(roomNumber);
        System.out.println(room);

        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter check-in date (DD/MM/YYYY): ");
        Date checkInDate = dateFormat.parse(scanner.nextLine());
        System.out.println("Enter check-out date (DD/MM/YYYY): ");
        Date checkOutDate = dateFormat.parse(scanner.nextLine());

        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        System.out.println(reservation);
    }

    private void seeReservations() {
        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        reservations.forEach(System.out::println);
    }

    private void createAccount() {
        System.out.println("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.println("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter customer last name: ");
        String lastName = scanner.nextLine();
        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("Customer account created!");
    }

    public void start() throws ParseException {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    reserveRoom();
                    break;
                case "2":
                    seeReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    AdminMenu adminMenu = new AdminMenu(scanner);
                    adminMenu.start();
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }
}
