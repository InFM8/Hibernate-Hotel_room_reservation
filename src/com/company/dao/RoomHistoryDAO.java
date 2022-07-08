package com.company.dao;

import com.company.entity.RoomHistory;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class RoomHistoryDAO {
    public RoomHistoryDAO () {}
    public void insertRooms(RoomHistory roomHistory) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(roomHistory);
        session.getTransaction().commit();
    }
    public List<RoomHistory> searchForRooms(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<RoomHistory> rooms;
        rooms = session.createQuery("FROM RoomHistory ").getResultList();
        System.out.println("\nVisited guests : "+rooms.size()+"\n");
        session.getTransaction().commit();
        return rooms;
    }
}
