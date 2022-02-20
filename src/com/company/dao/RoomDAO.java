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

    public int updateByStatusFalse(int id) {//Neveikia
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> list = new ArrayList<>();
        list = session.createQuery("UPDATE Room s SET e=s.in_use=false WHERE s.number="+id).getResultList();
                                    // update Student s set e=s.marks=50 where s.studentId=10;
        session.getTransaction().commit();
        return id;
    }
    public List<Room> showRooms(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Room> rooms;
        rooms = session.createQuery("SELECT r.number, r.in_use, g.name, g.surname FROM Room r JOIN r.guests g").getResultList();
        session.getTransaction().commit();
        return rooms;
    }

//    public List<Guest> showGuestRoom() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        List<Guest> guests;
//        guests = session.createQuery("SELECT c.number, c.in_use, i.name, i.surname FROM Room c " +
//                "JOIN c.guests i WHERE c.in_use=true").getResultList();
//
//        session.getTransaction().commit();
//        return guests;
//    }



//    public List<Room> printStatus(Room room) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List<Room> room;
//        room = session.createQuery("SELECT c.in_use FROM Room c WHERE c.number = "+room).getResultList();
//        return room;
//    }


//    public int updateByID(int room, boolean status) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        List<Room> list = new ArrayList<>();
//        list = session.createQuery("UPDATE Room SET in_use ="+status+" WHERE number="+room).getResultList();
//
//        return room;
//    }

}
