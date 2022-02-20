package com.company.dao;

import com.company.entity.Guest;
import com.company.entity.Room;
import com.company.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public RoomDAO(){}
    public void insert (int room){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
    }
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
        session.beginTransaction(); //Transaction already active...
        session.update(room);
        session.getTransaction().commit();
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
        System.out.println(rooms);
        session.getTransaction().commit();
        return rooms;
    }
    public List<Room> occupiedRoomATM() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> rooms;               //SELECT g.room, g.name, g.surname, r.in_use
                                        //FROM Room r, Guest g WHERE r.in_use
        rooms = session.createQuery("SELECT g.room, g.name, g.surname, r.in_use FROM Room r, Guest g WHERE r.in_use=true").getResultList();
        session.getTransaction().commit();
        return rooms;
    }
}
