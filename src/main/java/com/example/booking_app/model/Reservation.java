package com.example.booking_app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    String firstName;

    String lastName;

    private LocalDateTime exirationDate;

    @OneToOne(mappedBy = "reservation")
    private CinemaSeat cinemaSeat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getExirationDate() {
        return exirationDate;
    }

    public void setExirationDate(LocalDateTime exirationDate) {
        this.exirationDate = exirationDate;
    }

    public CinemaSeat getCinemaSeat() {
        return cinemaSeat;
    }

    public void setCinemaSeat(CinemaSeat cinemaSeat) {
        this.cinemaSeat = cinemaSeat;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
