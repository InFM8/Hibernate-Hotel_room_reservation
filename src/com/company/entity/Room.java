package com.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kambariai")
public class Room {
    @Id
    @Column(name = "kambarys")
    private int number;

    @Column(name = "statusas")
    boolean in_use;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Guest> guests;

//    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
//    private List<Guest> guests;
    public Room(){}

    public Room(int number) {
        this.number = number;
    }

    public Room(int number, boolean in_use) {
        this.number = number;
        this.in_use = in_use;
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

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", in_use=" + in_use +
                ", guests=" + guests +
                '}';
    }
}