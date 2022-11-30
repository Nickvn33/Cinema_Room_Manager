package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static int rows;
    static int seats;
    static int ticketPrice;
    static int rowPurchase;
    static int seatPurchase;
    static int ticketsPurchased;
    static boolean validTicket;
    static int totalSeats;
    static float percentageBought;
    static int currentIncome;
    static int totalIncome;
    static String statMessage;

    public static void createCinema(char[][] cinema, int rows, int seats) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }
    }

    public static void printCinema(char[][] cinema, int rows, int seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int k = 1; k <= seats; k++) {
            System.out.print(k + " ");
        }
        System.out.print('\n');
        for (int l = 0; l < rows; l++) {
            System.out.print((l + 1) + " ");
            for (int m = 0; m < seats; m++) {
                System.out.print(cinema[l][m] + " ");
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    public static int buyTicket(char[][] cinema) {
        validTicket = false;
        while (!validTicket){
            System.out.println("Enter a row number:");
            rowPurchase = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatPurchase = scanner.nextInt();
            System.out.print('\n');
            if (rowPurchase < 1 || rowPurchase > cinema.length || seatPurchase < 1
            || seatPurchase > cinema[0].length) {
                System.out.println("Wrong Input\n");
            } else if (cinema[rowPurchase - 1][seatPurchase - 1] == 'B') {
                System.out.println("That ticket has already been purchased!\n");
            } else {
                cinema[rowPurchase - 1][seatPurchase - 1] = 'B';
                validTicket = true;
            }
        }
        return rowPurchase;
    }

    public static void calculateTicketPrice(int rowPurchase, int rows, int seats) {
        if (rows * seats <= 60) {
            ticketPrice = 10;
        } else {
            if (rowPurchase < (float) rows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

    public static void statistics(char[][] cinema, int rows, int seats) {
        totalSeats = rows * seats;
        ticketsPurchased = 0;
        currentIncome = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                if (cinema[i][j] == 'B') {
                    ticketsPurchased++;
                    if (totalSeats <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i + 1 < (float) rows / 2) {
                            currentIncome += 10;
                        } else {
                            currentIncome += 8;
                        }
                    }
                }
            }
        }
        percentageBought = ticketsPurchased / (float) (rows * seats) * 100;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (rows / 2 * seats * 10) + (((rows / 2) + (rows % 2)) * seats * 8);
        }
        statMessage = String.format("Number of purchased tickets: %d%nPercentage: %.2f%c%nCurrent Income: $%d%n" +
                        "Total Income: $%d",
                ticketsPurchased, percentageBought, '%', currentIncome, totalIncome);
        System.out.println(statMessage);
    }

    public static void prompt(char[][] cinema, int rows, int seats) {
        System.out.print('\n');
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a Ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int response = scanner.nextInt();
        System.out.print('\n');

        while (response != 0) {
            if (response == 1) {
                printCinema(cinema, rows, seats);
            } else if (response == 2) {
                buyTicket(cinema);
                calculateTicketPrice(rowPurchase, rows, seats);
            } else if (response == 3) {
                statistics(cinema, rows, seats);
            } else if (response < 0 || response > 3) {
                System.out.println("Wrong input!");
            }
            System.out.print('\n');
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a Ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            response = scanner.nextInt();
            System.out.print('\n');
        }
    }

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        char[][] cinema = new char[rows][seats];

        createCinema(cinema, rows, seats);
        prompt(cinema, rows, seats);
    }
}