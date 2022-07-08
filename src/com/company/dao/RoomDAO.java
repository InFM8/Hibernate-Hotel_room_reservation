package com.company.dao;

import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;


import java.util.List;

public class RoomDAO {
    public RoomDAO(){}

    public void insertRooms (Room room){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
    }

    public void update(Room room) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(room);
        session.getTransaction().commit();
    }

    public List<Room> updateRoomStatusTrueById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> rooms;
        rooms = session.createQuery("UPDATE Room r SET r.in_use=true WHERE r.number="+id).getResultList();
        session.getTransaction().commit();
        return rooms;
    }
    public Room searchByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Room room = session.get(Room.class, id);
        session.getTransaction().commit();
        return room;
    }
}
