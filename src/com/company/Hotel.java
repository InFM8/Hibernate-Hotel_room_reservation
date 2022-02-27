package com.company;

import com.company.dao.GuestDAO;
import com.company.dao.RoomDAO;
import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    RoomDAO roomDAO = new RoomDAO();
    GuestDAO guestDAO = new GuestDAO();
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
        boolean occupy = occupyRoom(room);

        System.out.println("Iveskite svecio varda : ");
        String name = sc.next();
        System.out.println("Iveskite svecio pavarde : ");
        String surname = sc.next();

        Guest guest = new Guest(name, surname);

        if (room == -1) {
            System.out.println("Siuo metu laisvu kambariu nera");
            return;
        }
//        if (!occupyRoom(room)) {
//            System.out.println("Numeris yra uzimtas, arba tokio numerio nera");
//            //return;
//        }
        System.out.println("Jusu kambario nr. yra " + room);
        //occupy
        Room room1 = new Room(room, true);
        roomDAO.update(room1);
        guest.setRoom(room1);

//        roomDAO.updateRoomStatusTrueById(room);
        guestDAO.insert(guest);

    }


    void roomList() {
        List<Room> rooms = roomDAO.searchForRooms();
        for (Room rList : rooms) {
            System.out.println("   " + rList);
        }
    }

    void guestList() {
        List<Guest> guests = guestDAO.searchForGuests();
        for (Guest gList : guests) {
            System.out.println("   " + gList);
        }
    }

    void regGuestByRoomId(int id) {
        Room room = new Room(id, true);
        room.setIn_use(true);
        roomDAO.update(room);
    }

    void unRegGuestByRoomId(int id) {

        Room room = new Room(id);
        room.setIn_use(false);
        roomDAO.update(room);
        System.out.println("Svecias sekmingai isregistruotas\n");
    }

    void unRegisterGuest() {
        System.out.println("Iveskite kambario numeri is kurio isvyko svecias : ");
        int s = sc.nextInt();
        unRegGuestByRoomId(s);

    }

    void func() {
        Hotel hotel = new Hotel();
        Scanner sc = new Scanner(System.in);
        System.out.println("   Pasirinkite galimus veiksmus\n" +
                "1. Svecio registracija\n" +
                "2. Svecio isregistravimas\n" +
                "3. Kambariu uzimtumo perziura\n" +
                "4. Kambario istorija\n" +
                ": ");
        int s = sc.nextInt();
        if (s == 1) {
            System.out.println("-     Svecio registracija\n");
            hotel.placeGuest();
        } else if (s == 2) {
            System.out.println("-     Svecio isregistravimas pagal kambario numeri\n");
            hotel.unRegisterGuest();
        } else if (s == 3) {
            System.out.println("-     Kambariu uzimtumo perziura\n");
            guestDAO.occupiedRoomAtTheMoment();
        } else if (s == 4) {
            System.out.println("-     Kambariu istorija ir statusas\n");
            hotel.guestList();
        } else {
            System.out.println("Blogai ivesta");
        }

        //roomDAO.searchForRooms(true);
        //hotel.roomList();
    }

    public static void main(String[] args) {
        Hotel h = new Hotel();
        RoomDAO roomDAO = new RoomDAO();
        GuestDAO guestDAO = new GuestDAO();
        //h.func();

        //roomDAO.updateRoomStatusTrueById(2);
        //roomDAO.searchStatusByInt(2);
        //guestDAO.occupiedRoomAtTheMoment();

        //guestDAO.setTrue(2);

         h.unRegGuestByRoomId(1);

        HibernateUtil.getSessionFactory().close();
    }
}

