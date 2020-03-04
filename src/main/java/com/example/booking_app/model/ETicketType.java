package com.example.booking_app.model;

public enum ETicketType {
    TICKET_ADULT(25),
    TICKET_STUDENT(18),
    TICKET_CHILD(12.50);

    private double price;

    ETicketType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
