package com.dexter;

import java.text.ParseException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner);
        mainMenu.start();
        scanner.close();
    }
}
