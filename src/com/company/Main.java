package com.company;

import com.company.dao.GuestDAO;
import com.company.dao.HotelDAO;
import com.company.entity.Guest;
import com.company.entity.Hotel;
import com.company.utils.HibernateUtil;

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

        Scanner sc = new Scanner(System.in);
        System.out.println("Iveskite svecio varda : ");
        String vardas = sc.next();
        System.out.println("Iveskite svecio pavarde : ");
        String pavarde = sc.next();

        Guest guest = new Guest(vardas,pavarde);
        int sk = (int) (Math.random() * 4);
        if(sk == 0) {
            guest.setHotel(room1);
        } else if(sk == 1) {
            guest.setHotel(room2);
        } else if(sk == 2) {
            guest.setHotel(room3);
        } else if(sk ==3) {
            guest.setHotel(room4);
        } else if(sk ==4) {
            guest.setHotel(room5);
        } else{
            System.out.println("Nera laisvu kambariu");
        }
//        hotelDAO.insert(room1);
//        hotelDAO.insert(room2);
//        hotelDAO.insert(room3);
//        hotelDAO.insert(room4);
//        hotelDAO.insert(room5);

//        guest.setHotel(room1);

//        guestDAO.insert(guest);


       // hotelDAO.searchForRooms();



        HibernateUtil.getSessionFactory().close();
    }
}
