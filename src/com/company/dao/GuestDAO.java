package com.company.dao;

import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import javafx.fxml.LoadException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.naming.directory.SearchResult;
import javax.persistence.EntityManager;
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

    public Guest searchByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Guest guest = session.get(Guest.class, id);
        session.getTransaction().commit();
        return guest;
    }
    public Guest searchGuestByRoomId(int id){
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Guest guests = (Guest) session.createQuery("FROM Guest g WHERE g.room.id=" + id).getSingleResult();
            session.getTransaction().commit();
            return guests;
    }

    public void delete(Guest guest) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(guest);
        session.getTransaction().commit();
    }

    public List<Guest> occupiedRoomAtTheMoment() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("FROM Guest g WHERE g.room.in_use=true").getResultList();

        System.out.println("\nOccupied rooms : "+guests.size()+"\n");

        for(Guest g: guests){
            System.out.println(g);
        }
        session.getTransaction().commit();
        return guests;
    }
    public List<Guest> setTrue(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("UPDATE Guest g SET g.room.in_use=true WHERE g.id=:"+id).getResultList();
        session.getTransaction().commit();
        return guests;
    }
}

