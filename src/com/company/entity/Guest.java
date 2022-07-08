package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name="guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//A.I.

    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @OneToOne()
    @JoinColumn(name="room_id")
    private Room room;
    public Guest(){}

    public Guest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Guest(String name, String surname, Room room) {
        this.name = name;
        this.surname = surname;
        this.room = room;
    }

    public Guest(int id, String name, String surname, Room room) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Guest {" +
                " name = '" + name + '\'' +
                ", surname ='" + surname + '\'' +
                ", " + room +
                '}';
    }
}

