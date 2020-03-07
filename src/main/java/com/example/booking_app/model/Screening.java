package com.example.booking_app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Screening implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_screening")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false)
    private Movie movie;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_cinemahall", nullable = false)
    private CinemaHall cinemaHall;

    public Screening(){};

    public Screening(Movie movie, LocalDateTime date, CinemaHall cinemaHall){
        this.movie=movie;
        this.date=date;
        this.cinemaHall=cinemaHall;
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
}
