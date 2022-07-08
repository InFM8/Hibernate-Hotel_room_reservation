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
            System.out.println("All rooms occupied at the moment");
            return;
        }
        if(occupyRoom(room)) {
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
            System.out.println("\nGuest room number is : " + room+"\n");
        }
    }

    void unRegGuestByRoomId(int id) {
        if (id < 1 || id > 5){
            System.out.println("Room with id : "+id+", doesn't exist");
            System.exit(0);
        }


        Guest guest = guestDAO.searchGuestByRoomId(id);
        Room room = new Room(id);
        if(guest!=null) {
            room.setIn_use(false);
            roomDAO.update(room);
            guestDAO.delete(guest);
            System.out.println("\nGuest from " + id + " room, successfully unregistered\n");
        }
    }

    void guestList() {
        List<RoomHistory> guests = roomHistoryDAO.searchForRooms();
        for (RoomHistory gList : guests) {
            System.out.println(" " + gList);
        }
    }

    void unRegisterGuest() {
        System.out.println("Enter a room number which is empty : ");
        int num = sc.nextInt();

        unRegGuestByRoomId(num);
    }

    void func() {
        Main hotel = new Main();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n   Select the available actions\n" +
                "1. Registering a guest\n" +
                "2. Unregistering a guest\n" +
                "3. Occupied rooms(status)\n" +
                "4. History of rooms\n" +
                ": ");
        int s = sc.nextInt();
        if (s == 1) {
            System.out.println("-     Registering a guest\n");
            hotel.placeGuest();
        } else if (s == 2) {
            System.out.println("-     Unregistering a guest by room id\n");
            hotel.unRegisterGuest();
        } else if (s == 3) {
            System.out.println("-     Occupied rooms at the moment\n");
            guestDAO.occupiedRoomAtTheMoment();

        } else if (s == 4) {
            System.out.println("-     History of rooms and status\n");
            hotel.guestList();
        } else {
            System.out.println("Wrong input");
            System.exit(0);

        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.func();

        System.out.println("\nWill you continue working? (yes/no): \n");
        String ask = main.sc.next();
        if(!ask.equals("yes")) {
            System.out.println("App is shutting down");
            System.exit(0);
        }
        while (ask.toLowerCase(Locale.ROOT).equals("yes")) {
        main(args);
        HibernateUtil.getSessionFactory().close();
        }

        HibernateUtil.getSessionFactory().close();
    }
}

