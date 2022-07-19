package com.company;

import com.company.dao.GuestDAO;
import com.company.dao.RoomDAO;
import com.company.dao.RoomHistoryDAO;
import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.entity.RoomHistory;
import com.company.utils.HibernateUtil;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    RoomDAO roomDAO = new RoomDAO();
    GuestDAO guestDAO = new GuestDAO();
    RoomHistoryDAO roomHistoryDAO = new RoomHistoryDAO();
    Scanner sc = new Scanner(System.in);
    Room id1 = roomDAO.searchByID(1);
    Room id2 = roomDAO.searchByID(2);
    Room id3 = roomDAO.searchByID(3);
    Room id4 = roomDAO.searchByID(4);
    Room id5 = roomDAO.searchByID(5);

    Room rooms[] = {
            new Room(id1.getNumber(), id1.isIn_use()),
            new Room(id2.getNumber(), id2.isIn_use()),
            new Room(id3.getNumber(), id3.isIn_use()),
            new Room(id4.getNumber(), id4.isIn_use()),
            new Room(id5.getNumber(), id5.isIn_use())
    };

    int findFreeRoom() {
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i].isIn_use()) {
                return rooms[i].getNumber();
            }
        }
        return -1;
    }

    boolean occupyRoom(int number) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getNumber() != number) {
                continue;
            }
            if (!rooms[i].isIn_use()) {
                rooms[i].setIn_use(true);
                return true;
            }
        }
        return false;
    }

    void placeGuest() {
        int room = findFreeRoom();

        if (room == -1) {
            System.err.println("All rooms occupied at the moment");
            return;
        }
        if (occupyRoom(room)) {
            System.out.println("Enter a guest name : ");
            String name = sc.next();
            System.out.println("Enter a guest surname : ");
            String surname = sc.next();

            Guest guest = new Guest(name, surname);
            RoomHistory roomHistory = new RoomHistory(name, surname, room);
            roomHistoryDAO.insertRooms(roomHistory);
            Room room1 = new Room(room, true);
            roomDAO.update(room1);
            guest.setRoom(room1);
            guestDAO.insert(guest);
            System.out.println(TEXT_GREEN+"\nRegistered successfully"+TEXT_RESET);
            System.out.println("\nWelcome, "+TEXT_YELLOW+name+" "+surname+ TEXT_RESET+", you room number is : " + room + "\n");
        }
    }

    void unRegGuestByRoomId(int id) {
        try {
            Guest guest = guestDAO.searchGuestByRoomId(id);
            Room room = new Room(id);

            room.setIn_use(false);
            roomDAO.update(room);
            guestDAO.delete(guest);
            System.out.println(TEXT_GREEN+"\nSuccessfully unregistered"+TEXT_RESET);
            System.out.println("\nGuest - "+TEXT_YELLOW+guest.getName() +" "+ guest.getSurname()+ TEXT_RESET+
                    " from "+TEXT_YELLOW + id + TEXT_RESET+ " room checked out.\n");

        } catch (javax.persistence.PersistenceException e) {
            System.err.println("Can't unregister guest from room " + id + ", because it's empty! ");

            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                System.err.println("exception: " + e);
            }
        }
    }

    void guestList() {
        List<RoomHistory> guests = roomHistoryDAO.searchForRooms();
        for (RoomHistory gList : guests) {
            System.out.println(" " + gList);
        }
    }

    void unRegisterGuest() {
        System.out.println("Enter a room number that the guest checked out :\n ");

        String num = sc.next();
        if(num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4")) {
            unRegGuestByRoomId(Integer.parseInt(num));
        } else {
            System.err.println("Wrong input, you entered :\n");
            System.out.println(num);
            func();
        }
    }

    void func() {
        Main hotel = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.println(TEXT_GREEN+"\n   Select available options\n" +TEXT_RESET+
                "1. Register a guest\n" +
                "2. Unregister a guest\n" +
                "3. Occupied rooms(status)\n" +
                "4. History of rooms\n" +
                ": ");
        String s = sc.next();
        if (s.equals("1")) {
            System.out.println("-     Register a guest\n");
            hotel.placeGuest();
        } else if (s.equals("2")) {
            System.out.println("-     Unregister a guest by room id\n");
            hotel.unRegisterGuest();
        } else if (s.equals("3")) {
            System.out.println("-     Occupied rooms at the moment\n");
            guestDAO.occupiedRoomAtTheMoment();
        } else if (s.equals("4")) {
            System.out.println("-     History of rooms and status\n");
            hotel.guestList();
        } else {
            System.err.println("\nWrong input, you entered : \n");
            System.out.println(s);
            func();
        }
    }
    public static final String TEXT_YELLOW = "\u001B[33m";

    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";

    public static void main(String[] args) {
        Main main = new Main();

        main.func();

        System.out.println("\nWill you continue working? (yes/no): \n");
        String ask = main.sc.next();
        if (!ask.toLowerCase(Locale.ROOT).equals("yes")) {
            System.err.println("Shutting down");
            System.exit(0);
        }
        while (ask.toLowerCase(Locale.ROOT).equals("yes")) {
            main(args);
            HibernateUtil.getSessionFactory().close();
        }

        HibernateUtil.getSessionFactory().close();
    }
}

