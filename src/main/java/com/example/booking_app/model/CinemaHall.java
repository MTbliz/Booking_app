package com.example.booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class CinemaHall implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinemahall")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
