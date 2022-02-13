package com.company;

import com.company.dao.GuestDAO;
import com.company.dao.HotelDAO;
import com.company.entity.Guest;
import com.company.entity.Hotel;
import com.company.utils.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        GuestDAO guestDAO = new GuestDAO();

        Hotel room1 = new Hotel(1);
        Hotel room2 = new Hotel(2);
        Hotel room3 = new Hotel(3);
        Hotel room4 = new Hotel(4);
        Hotel room5 = new Hotel(5);

        boolean[] rooms = new boolean[5];

        Scanner sc = new Scanner(System.in);
        System.out.println("Iveskite svecio varda : ");
        String vardas = sc.next();
        System.out.println("Iveskite svecio pavarde : ");
        String pavarde = sc.next();
        Guest guest = new Guest(vardas, pavarde);

        List<Hotel> list = hotelDAO.searchForRooms();
        for(Hotel roomList: list) {
            System.out.println(roomList);
        }

//        boolean status = hotelDAO.searchForStatus(false);
//        boolean found = false;


//        for (int i = 0; i < rooms.length; i++) {
//            if (rooms[0] == status) {
//                guest.setHotel(room1);
//                guestDAO.insert(guest);
//
//                Hotel notEmpty = new Hotel(1, true);
//                hotelDAO.update(notEmpty);
//                found = true;
//                continue;
//            } else if (rooms[1] == status) {
//                guest.setHotel(room2);
//                guestDAO.insert(guest);
//                Hotel true2 = new Hotel(2, true);
//                hotelDAO.update(true2);
//                System.out.println("Jusu kambarys yra antras");
//                continue;
//

//            } else {
//                System.out.println("Nera laisvu kambariu");
//                guestDAO.delete(guest);
//            }

        hotelDAO.insert(room1);
        hotelDAO.insert(room2);
        hotelDAO.insert(room3);
        hotelDAO.insert(room4);
        hotelDAO.insert(room5);


//        Guest guest1 = guestDAO.searchByRoomID(1);
//        System.out.println(guest1);


        // hotelDAO.searchForRooms();


        HibernateUtil.getSessionFactory().close();

    }
}
