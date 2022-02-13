package com.company.dao;

import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class RoomDAO {
    public RoomDAO(){}
    public void insert (int room){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(room);

        session.getTransaction().commit();

    }
//    public void insert (Room room){
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        session.save(room);
//
//        session.getTransaction().commit();
//
//    }

    public List<Room> searchForRooms(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Room> rooms;
        rooms = session.createQuery("FROM Room").getResultList();

        return rooms;
    }
    public void update(Room room) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.update(room);

        session.getTransaction().commit();
    }

    public boolean searchForStatus(boolean status){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Room> rooms;
            rooms = session.createQuery("FROM Room c WHERE c.in_use = "+status).getResultList();

            return status;
    }
//    public List<Room> insertList(){
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        List<Room> rooms;
//        rooms = session.createQuery("CREATE TABLE IF NOT EXISTS "+Room+"(" +
//                "")
//
//    }
}
