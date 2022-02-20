package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name="sveciai")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//A.I.

    @Column(name="id")
    private int id;

    @Column(name="vardas")
    private String name;

    @Column(name="pavarde")
    private String surname;

    @ManyToOne()
    @JoinColumn(name="kambario_id")
    private Room room;
    public Guest(){}

    public Guest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Guest(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", room=" + room +
                '}';
    }
}
//    @Override
//    public String toString() {
//        return "Guest{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                '}';
//    }