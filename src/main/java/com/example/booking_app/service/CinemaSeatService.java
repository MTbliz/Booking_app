package com.example.booking_app.service;

import com.example.booking_app.model.CinemaHall;
import com.example.booking_app.model.CinemaSeat;
import com.example.booking_app.model.Screening;
import java.util.List;

public interface CinemaSeatService {
    CinemaSeat create(CinemaSeat cinemaSeat);

    CinemaSeat read(Long cinemaSeatId);

    void update(Long cinemaSeatId, CinemaSeat cinemaSeat);

    void delete(Long cinemaSeatId);

    Iterable<CinemaSeat> getAllCinemaSeats();

    List<CinemaSeat> getCinemaSeatsByScreening(Screening screening);

    List<CinemaSeat> findReservedSeats();

    boolean checkIfBlankBetweenReservedSeats(List<CinemaSeat> seatsList);

    boolean checkIfSeatIsReserved(CinemaSeat seat);

    String printReservationStatus(String firstName, String lastName, List <Long> listOfSeats, int adult, int student, int child);
}
