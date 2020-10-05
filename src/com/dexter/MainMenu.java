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
    HotelResource hotelResource = HotelResource.getInstance();

    private void reserveRoom() throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        IRoom room = hotelResource.getRoom(roomNumber);
        System.out.println(room);

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Enter check-in date (DD/MM/YYYY): ");
        Date checkInDate = dateFormat.parse(scanner.nextLine());
        System.out.print("Enter check-out date (DD/MM/YYYY): ");
        Date checkOutDate = dateFormat.parse(scanner.nextLine());

        Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
        System.out.println(reservation);
        scanner.close();
    }

    private void seeReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        reservations.forEach(System.out::println);
        scanner.close();
    }

    private void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer last name: ");
        String lastName = scanner.nextLine();
        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("Customer account created!");
        scanner.close();
    }

    public void start() throws ParseException {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    reserveRoom();
                    break;
                case 2:
                    seeReservations();
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
            scanner.close();
        }
    }
}
