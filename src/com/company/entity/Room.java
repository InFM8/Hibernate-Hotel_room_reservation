package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @Column(name = "room")
    private int number;

    @Column(name = "status")
    boolean in_use;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Guest guests;
    public Room(){}

    public Room(int number) {
        this.number = number;
    }

    public Room(int number, boolean in_use) {
        this.number = number;
        this.in_use = in_use;
    }

    public Room(int number, boolean in_use, Guest guests) {
        this.number = number;
        this.in_use = in_use;
        this.guests = guests;
    }

    public Room(boolean in_use, Guest guests) {
        this.in_use = in_use;
        this.guests = guests;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIn_use() {
        return in_use;
    }

    public void setIn_use(boolean in_use) {
        this.in_use = in_use;
    }

    public Guest getGuests() {
        return guests;
    }

    public void setGuests(Guest guests) {
        this.guests = guests;
    }
    @Override
    public String toString() {
        return "Room {" +
                "number:  " + number +
                ", At the moment: " + (in_use ? " Occupied" : " Empty") +
                '}';
    }
}