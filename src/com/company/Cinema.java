package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    static List<Integer> income = new ArrayList<>();
    static boolean online = true;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows: ");
        int rowsNum = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int seatsNum = scanner.nextInt();

        char[][] room = new char[rowsNum][seatsNum];

        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < seatsNum; j++) {
                room[i][j] = 'S';
            }
        }

        showMenu();
        int menuNumber = scanner.nextInt();

        while (online) {
            if (menuNumber == 1) {
                printRoom(room);
                showMenu();
                menuNumber = scanner.nextInt();
            } else if (menuNumber == 2) {
                ticketPrice(room);
                showMenu();
                menuNumber = scanner.nextInt();
            } else if (menuNumber == 3) {
                showStatistics(room);
                showMenu();
                menuNumber = scanner.nextInt();
            } else {
                online = false;
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void showStatistics(char[][] room) {
        int rowsNum = room.length;
        int seatsNum = room[0].length;

        int purchasedTickets = 0;
        double percentage;
        int currentIncome = 0;
        int totalIncome = priceSum(room);

        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < seatsNum; j++) {
                if (room[i][j] == 'B') {
                    purchasedTickets += 1;
                }
            }
        }

        for (Integer current : income) {
            currentIncome += current;
        }

        percentage = (purchasedTickets / ((double) rowsNum * seatsNum)) * 100;

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void printRoom(char[][] room) {
        int rowsNum = room.length;
        int seatsNum = room[0].length;

        System.out.println("Cinema: ");
        System.out.print("  ");
        for (int i = 1; i <= seatsNum; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < rowsNum; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsNum; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void ticketPrice(char[][] room) {
        int rows = room.length;
        int seats = room[0].length;
        try {

            System.out.println("Enter a row number: ");
            int row = scanner.nextInt();

            System.out.println("Enter a seat number in that row: ");
            int seat = scanner.nextInt();

            if (room[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                ticketPrice(room);
            } else {

                if (rows * seats <= 60) {
                    System.out.println("Ticket price: $10");
                    income.add(10);
                } else {
                    int half = rows / 2;

                    if (row <= half) {
                        System.out.println("Ticket price: $10");
                        income.add(10);
                    } else {
                        System.out.println("Ticket price: $8");
                        income.add(8);
                    }
                }
                room[row - 1][seat - 1] = 'B';

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input!");
            ticketPrice(room);
        }
    }

    public static int priceSum(char[][] room) {
        int sum = 0;

        int rowsNum = room.length;
        int seatsNum = room[0].length;

        if (rowsNum * seatsNum <= 60) {
            sum += rowsNum * seatsNum * 10;
        } else {

            if (rowsNum * seatsNum % 2 == 0) {
                int firstHalf = seatsNum * rowsNum / 2;
                sum += firstHalf * 10;
                sum += firstHalf * 8;
            } else {
                int firstHalf = rowsNum / 2;
                int secondHalf = rowsNum - firstHalf;
                sum += firstHalf * seatsNum * 10;
                sum += secondHalf * seatsNum * 8;
            }
        }

        return sum;
    }
}
