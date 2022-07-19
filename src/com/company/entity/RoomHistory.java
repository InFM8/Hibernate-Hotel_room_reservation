package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name = "History")
public class RoomHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "room")
    private int room;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;


    public RoomHistory(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public RoomHistory(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public RoomHistory(int id, String name, String surname, int room) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.room = room;
    }

    public RoomHistory(String name, String surname, int room) {
        this.name = name;
        this.surname = surname;
        this.room = room;
    }

    public RoomHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";
    @Override
    public String toString() {
        return "History {" +
                "id : " + id +
                ", name : '"+TEXT_YELLOW + name + TEXT_RESET +
                ", surname : '"+TEXT_YELLOW + surname + TEXT_RESET +
                ", room : "+TEXT_YELLOW + room + TEXT_RESET+
                '}';
    }

}
