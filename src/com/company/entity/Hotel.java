package com.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kambariai")
public class Hotel {
    @Id

    @Column(name="kambarys")
    private int room;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Guest> guests;

    public Hotel(){}

    public Hotel(int room) {
        this.room = room;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "room=" + room +
                ", guests=" + guests +
                '}';
    }
}

