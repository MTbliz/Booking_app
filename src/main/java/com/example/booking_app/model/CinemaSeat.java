package com.example.booking_app.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CinemaSeat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat")
    private Long id;

    private int seatNumber;
    private int seatRow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_screening", nullable = false)
    private Screening screening;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reservation", referencedColumnName = "id_reservation")
    private Reservation reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    }
