package com.example.booking_app.model;

import javax.validation.Valid;
import java.util.List;

public class ReservationData {

    @Valid
    User user;

    List<Long> listOfSeats;

    int adult;

    int student;

    int child;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getListOfSeats() {
        return listOfSeats;
    }

    public void setListOfSeats(List<Long> listOfSeat) {
        this.listOfSeats = listOfSeat;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }
}
