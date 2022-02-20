package com.company.dao;

import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import javafx.fxml.LoadException;
import org.hibernate.Session;

import javax.naming.directory.SearchResult;
import java.util.List;

public class GuestDAO {
    public GuestDAO() {
    }

    public void insert(Guest guest) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(guest);
        session.getTransaction().commit();
    }
    public List<Guest> updateGuestStatusByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("UPDATE Guest g SET g.room.in_use=true WHERE g.id="+id).getResultList();
        session.getTransaction().commit();
        return guests;
    }

    public List<Guest> showGuestRoom() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("SELECT c.number, c.in_use, i.name, i.surname FROM Room c JOIN c.guests i WHERE c.in_use=true").getResultList();
        session.getTransaction().commit();
        return guests;
    }

    public List<Guest> showGuestRoom1() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("FROM Guest i JOIN i.room c WHERE c.in_use=true").getResultList();
        session.getTransaction().commit();
        return guests;
    }


    public Guest searchByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Guest guest = session.get(Guest.class, id);
        session.getTransaction().commit();
        return guest;
    }

    public void delete(Guest guest) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(guest);
        session.getTransaction().commit();
    }

    public List<Guest> searchForGuests() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("FROM Guest").getResultList();
        session.getTransaction().commit();
        return guests;
    }

    public List<Guest> occupiedRoomAtTheMoment() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("FROM Guest g WHERE g.room.in_use=true").getResultList();
        for(Guest g: guests){
            System.out.println(g);
        }
        //FROM Room r JOIN r.guests g WHERE r.in_use=true
        //FROM Room r, Guest g WHERE r.in_use=true
        session.getTransaction().commit();
        return guests;
    }
}
//    public List<Room> occupiedRoomATM() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List<Room> rooms;               //SELECT g.room, g.name, g.surname, r.in_use
//        //FROM Room r, Guest g WHERE r.in_use=true
//        //SELECT g.room, g.name, g.surname, r.in_use FROM Room r JOIN r.guests g WHERE r.in_use=true
//        rooms = session.createQuery("SELECT g.room, g.name, g.surname, r.in_use FROM Room r, Guest g WHERE r.in_use=true").getResultList();
//        session.getTransaction().commit();
//        return rooms;
//    }
