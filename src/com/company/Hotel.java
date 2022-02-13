package com.company;

import com.company.dao.GuestDAO;
import com.company.dao.RoomDAO;
import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;

import java.util.Scanner;

public class Hotel {
    RoomDAO roomDAO = new RoomDAO();
    GuestDAO guestDAO = new GuestDAO();

    boolean status = roomDAO.searchForStatus(true);

    Room rooms[] = {
            new Room(1, false),
            new Room(2, false),
            new Room(3, status),
            new Room(4, status),
            new Room(5, status)
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
        boolean occupy = occupyRoom(room);

        GuestDAO guestDAO = new GuestDAO();
        RoomDAO roomDAO = new RoomDAO();

        Scanner sc = new Scanner(System.in);
        System.out.println("Iveskite svecio varda : ");
        String name = sc.next();
        System.out.println("Iveskite svecio pavarde : ");
        String surname = sc.next();

        Guest guest = new Guest(name, surname);

        if (room == -1) {
            System.out.println("Siuo metu laisvu kambariu nera");
            //guestDAO.delete(guest);
            return;
        }
        if (!occupyRoom(room)) {
            System.out.println("Numeris yra uzimtas, arba tokio nr nera");
            //guestDAO.delete(guest);
            //return;
        }
        System.out.println("Jusu kambario nr. yra " + room);


        Room room1 = new Room(room,occupy);

        roomDAO.update(room1);   //Insertinam, updatinam jei atitinka salygas(Jei neegzistuoja insert.)
        guest.setRoom(room1);    //Priskiriam kambari klientui
        guestDAO.insert(guest); //Insertinam
    }

    public static void main(String[] args) {
        Hotel room = new Hotel();
        RoomDAO roomDAO = new RoomDAO();
        GuestDAO guestDAO = new GuestDAO();



        room.placeGuest();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Iveskite svecio varda : ");
//        String name = sc.next();
//        System.out.println("Iveskite svecio pavarde : ");
//        String surname = sc.next();
//
//        Guest g = new Guest(name, surname);


        //roomDAO.updateByID(0,5);




//  1.      roomDAO.update(room1);
//  1.      roomDAO.insert(room1);
//  2.      g.setRoom(room1);
//  3.      guestDAO.insert(g);






        HibernateUtil.getSessionFactory().close();
    }
}
