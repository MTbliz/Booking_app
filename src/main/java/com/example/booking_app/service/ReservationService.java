package com.example.booking_app.service;

import com.example.booking_app.model.Reservation;

public interface ReservationService {
    Reservation create(Reservation reservation);

    Reservation read(Long reservationId);

    void update(Long reservationId, Reservation reservation);

    void delete(Long reservationId);

    Iterable<Reservation> getAllReservations();
}
