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

    public boolean searchForRooms(boolean free) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Room> list;
        list = session.createQuery("FROM Room c WHERE c.in_use="+free).getResultList();
        for(Room freeRooms : list) {
            System.out.println(freeRooms);
        }
        return free;
    }

    public List<Room> searchStatusByInt(int num) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> rooms;
        rooms = session.createQuery("SELECT c.in_use FROM Room c WHERE c.number="+num).getResultList();

        System.out.println("Status : "+rooms);

        session.getTransaction().commit();
        return rooms;
    }
}
