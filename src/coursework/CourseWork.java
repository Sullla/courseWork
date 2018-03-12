package coursework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class CourseWork {

    String rooms;
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String roomName = null;
        String removeName = null;
        String ans;
        int roomNum = 0;
        int x = 0;
        CourseWork[] hotel;
        hotel = new CourseWork[11];
        initialise(hotel);

        while (roomNum < 11) {
            System.out.println("--------------------");
            System.out.println("------>WELCOME<-----");
            System.out.println("Press V to view all rooms\nPress E to view empty rooms\nPress A to book a room\nPress D to delete a customer from a room "
                    + "\nPress F to find a room by customers name"
                    + "\nPres S to store program data into file\nPres L to load program data from file\nPresss O to view rooms alphabetically by name");

            switch (ans = input.next()) {
                case "V":
                case "v":
                    viewRoom(hotel);

                    break;
                case "E":
                case "e":
                    viewEmptyRooms(hotel);

                    break;
                case "A":
                case "a":
                    bookRoom(hotel, roomNum, roomName, ans);
                    break;
                case "D":
                case "d":
                    removeCustomer(ans, hotel, roomNum, removeName);
                    break;
                case "F":
                case "f":

                    searchRoom(roomName, hotel);

                    break;
                case "S":
                case "s":
                    storeData(hotel, roomNum);
                    break;
                case "L":
                case "l":

                    loadData();
                    break;
                case "o":
                case "O":
                    alphabetOrder(hotel, roomName);
                    break;

                default:
                    System.out.println(" ");
                    System.out.println("WRONG!!!");
                    System.out.println("  ");

            }

        }
    }

    public static void initialise(CourseWork[] hotelRef) {
        for (int y = 0; y < 11; y++) {
            hotelRef[y] = new CourseWork();
        }
        for (int x = 0; x < 11; x++) {
            hotelRef[x].rooms = "e";

        }
    }

    public static void viewRoom(CourseWork[] hotelRef) {

        for (int x = 0; x < 11; x++) {
            System.out.println("Room " + x + " occupied by " + hotelRef[x].rooms);
            System.out.println("--------------------");
        }
    }

    public static void viewEmptyRooms(CourseWork[] hotelRef) {
        for (int x = 0; x < 11; x++) {

            if (hotelRef[x].rooms.equals("e")) {
                System.out.println("Room " + x + " is empty");
                System.out.println("--------------------");
            }
        }

    }

    public static void bookRoom(CourseWork[] hotelRef, int roomNum, String roomName, String aWord) {

        for (int lol = 0; lol < 11; lol++) {

            for (int cool = 0; cool < 1; cool++) {
                System.out.println("Enter room number (0-10):");
                roomNum = input.nextInt();
                System.out.println("--------------------");
                System.out.println("Enter name for room" + " " + roomNum + " :");

                roomName = input.next();

                hotelRef[roomNum].rooms = roomName;

                for (int x = 0; x < 11; x++) {
                    System.out.println("--------------------");
                    System.out.println("room " + x + " occupied by " + hotelRef[x].rooms);
                }

            }
            for (int count = 0; count < 11; count++) {
                System.out.println("--------------------");
                System.out.println("Would you like to continiue ordering rooms?");
                aWord = input.next();
                if (aWord.equals("Yes")) {
                    System.out.println("--------------------");
                    System.out.println("Enter room number (0-10):");

                    roomNum = input.nextInt();
                    System.out.println("--------------------");
                    System.out.println("Enter name for room" + " " + roomNum + " :");
                    roomName = input.next();
                    hotelRef[roomNum].rooms = roomName;
                    for (int x = 0; x < 11; x++) {
                        System.out.println("--------------------");
                        System.out.println("Room " + x + " occupied by " + hotelRef[x].rooms);
                    }

                } else if (aWord.equals("No")) {
                    lol = 11;
                    System.out.println("--------------------");
                    System.out.println("Returning back to main menu");
                    System.out.println(" ");
                }
                if (aWord.equals("No")) {
                    break;
                }

            }
        }
    }

    public static void removeCustomer(String ans, CourseWork[] hotelRef, int roomNum, String removeName) {
        System.out.println("--------------------");
        System.out.println("Would you like remove a customer from a room?");
        System.out.println("(Type Yes or No )");
        ans = input.next();

        if (ans.equals("Yes")) {
            System.out.println("--------------------");
            System.out.println("Then type customer's name please");
            removeName = input.next();
            for (int y = 0; y < 11; y++) {
                if (hotelRef[y].rooms.equals(removeName)) {
                    hotelRef[y].rooms = "e";
                    System.out.println("The customer was removed succesfully!");

                }

            }
        }
        if (ans.equals("No")) {
            System.out.println("--------------------");
            System.out.println("Then name will not be removed");
            System.out.println("--------------------");
            for (int x = 0; x < 11; x++) {
                System.out.println("--------------------");
                System.out.println("room" + x + "occupied by " + hotelRef[x]);
                System.out.println("--------------------");
            }
        }

    }

    public static void searchRoom(String roomName, CourseWork[] hotelRef) {

        System.out.println("Enter a customer's name please");

        roomName = input.next();

        for (int x = 0; x < 11; x++) {
            if (hotelRef[x].rooms.equals(roomName)) {
                System.out.println("--------------------");
                System.out.println("This is the room " + x + " you are looking for");
                System.out.println("--------------------");
            }

        }
    }

    public static void storeData(CourseWork[] hotelRef, int roomNum) {

        try {
            PrintWriter outtoFile = new PrintWriter("Hotel.dat");

            for (int x = 0; x < 11; x++) {
                if (!hotelRef[x].rooms.equals("e")) {

                    outtoFile.println(hotelRef[x].rooms);
                    outtoFile.println("--------------------");
                }

            }
            System.out.println("--------------------");
            System.out.println("The names was stored in 'Hotel.dat'");
            System.out.println("--------------------");
            outtoFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!!!");
        }

    }

    public static void loadData() {
        try {

            File hotelFile = new File("Hotel.dat");
            Scanner hotelData = new Scanner(hotelFile);

            System.out.println("our costumers are:");
            do {
                System.out.println(hotelData.nextLine());
            } while (hotelData.hasNextLine());

        } catch (Exception e) {
            System.out.println("ERROR!!");
        }
    }

    public static void alphabetOrder(CourseWork[] hotelRef, String roomName) {
        ArrayList<String> alphabet = new ArrayList<String>();

        for (int x = 0; x < 11; x++) {

            alphabet.add(hotelRef[x].rooms);
        }
        Collections.sort(alphabet);

        for (String str : alphabet) {

            if (!str.equals("e")) {

                System.out.println(" The room occupied by " + str);
            }

        }
    }

}
