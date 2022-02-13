package com.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="kambariai")
public class Hotel {
    @Id

    @Column(name="kambarys")
    private int room;

    @Column(name="statusas")
    private boolean status;

    @OneToMany(mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Guest> guests;

    public Hotel(){}

    public Hotel(int room) {
        this.room = room;
    }

    public Hotel(int room, boolean status) {
        this.room = room;
        this.status = status;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
                ", status=" + status +
                ", guests=" + guests +
                '}';
    }
}

