package com.company;

import com.company.dao.RoomDAO;
import com.company.entity.Room;

/**
 * At first create Rooms to test registration form.
 * (susikurkite nauja DB pavadinimu hotel ir paleiskite sia klase, kad sukurtumete viesbucio kambarius)
 */
public class Create {
    RoomDAO roomDAO = new RoomDAO();

    public void insertRooms() {
        Room r1 = new Room(1, false);
        Room r2 = new Room(2, false);
        Room r3 = new Room(3, false);
        Room r4 = new Room(4, false);
        Room r5 = new Room(5, false);
        roomDAO.insertRooms(r1);
        roomDAO.insertRooms(r2);
        roomDAO.insertRooms(r3);
        roomDAO.insertRooms(r4);
        roomDAO.insertRooms(r5);
//        Room r01 = new Room(10);
//        Room r02 = new Room(20);
//        Room r03 = new Room(30);
//        Room r04 = new Room(40);
//        Room r05 = new Room(50);
//        roomDAO.insertRooms(r01);
//        roomDAO.insertRooms(r02);
//        roomDAO.insertRooms(r03);
//        roomDAO.insertRooms(r04);
//        roomDAO.insertRooms(r05);
    }

    public static void main(String[] args) {
        Create c = new Create();
        c.insertRooms();
    }
}
