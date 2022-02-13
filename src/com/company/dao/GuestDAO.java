package com.company.dao;

import com.company.entity.Guest;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

public class GuestDAO {
    public GuestDAO(){}
    public void insert(Guest guest){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(guest);

        session.getTransaction().commit();

    }
    public Guest searchByRoomID(int hotel){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Guest guest = session.get(Guest.class, hotel);

        session.getTransaction().commit();

        return guest;
    }
    public void delete(Guest guest) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.delete(guest);

        session.getTransaction().commit();
    }
}
