package com.company.dao;

import com.company.entity.Hotel;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    public HotelDAO() {}

    public void insert(Hotel hotel) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(hotel);

        session.getTransaction().commit();
    }
    public List<Hotel> searchForRooms() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Hotel> hotels;
        hotels = session.createQuery("FROM Hotel").getResultList();

        return hotels;
    }
    public void update(Hotel hotel){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.update(hotel);

        session.getTransaction().commit();
    }
    public boolean searchForStatus(boolean status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Hotel> hotels;
        hotels = session.createQuery("FROM Hotel c WHERE c.status ="+status).getResultList();

        return status;
    }



}
