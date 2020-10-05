package com.dexter;

import com.dexter.api.AdminResource;
import com.dexter.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    AdminResource adminResource = AdminResource.getInstance();

    public void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        customers.forEach(System.out::println);
    }

    public void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        rooms.forEach(System.out::println);
    }

    public void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    public void addARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Enter room price: ");
        Double price = scanner.nextDouble();
        System.out.print("Enter room type - SINGLE(1) / DOUBLE(2): ");
        RoomType roomType = scanner.nextInt() == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
        IRoom room = new Room(roomNumber, price, roomType);
        List<IRoom> rooms = new ArrayList<>();
        rooms.add(room);
        adminResource.addRooms(rooms);
        scanner.close();
    }

    public void start() {
        boolean exit = false;
        while(!exit) {
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    seeAllCustomers();
                    break;
                case 2:
                    seeAllRooms();
                    break;
                case 3:
                    seeAllReservations();
                    break;
                case 4:
                    addARoom();
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
