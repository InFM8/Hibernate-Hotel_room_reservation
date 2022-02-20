package com.company.dao;

import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;
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

    public Guest searchByGuestID(int hotel) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Guest guest = session.get(Guest.class, hotel);
        session.getTransaction().commit();
        return guest;
    }

    public List<Guest> showGuestRoom() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Guest> guests;
        guests = session.createQuery("SELECT c.number, c.in_use, i.name, i.surname FROM Room c JOIN c.guests i WHERE c.in_use=true").getResultList();
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
}