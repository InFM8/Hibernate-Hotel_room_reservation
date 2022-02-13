package com.company.dao;

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
//        session.beginTransaction(); //Transaction already active...

        session.update(room);

        session.getTransaction().commit();
    }
    public Room searchByID(int room) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Room r = session.get(Room.class, room);
        session.getTransaction().commit();
        return r;
    }
    public boolean searchForStatus(boolean status){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Room> rooms;
            rooms = session.createQuery("FROM Room c WHERE c.in_use = "+status).getResultList();
            return status;
    }
    //Ieskosim tusciu, arba uzimtu kambariu.
    public Room searchRoomsByStatus(boolean status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Room room = session.get(Room.class, status);
        session.getTransaction().commit();
        return room;
    }
    public int updateByID(int room, int status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Room> list = new ArrayList<>();
        list = session.createQuery("UPDATE Room SET in_use ="+status+" WHERE number="+room).getResultList();

        return room;
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
