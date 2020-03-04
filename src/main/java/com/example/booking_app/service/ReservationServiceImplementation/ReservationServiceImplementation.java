package com.example.booking_app.service.ReservationServiceImplementation;

import com.example.booking_app.model.Reservation;
import com.example.booking_app.repository.ReservationRepository;
import com.example.booking_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImplementation implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationServiceImplementation() {
        super();
    }

    @Override
    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation read(Long reservationId) {
        return null;
    }

    @Override
    public void update(Long reservationId, Reservation reservation) {

    }

    @Override
    public void delete(Long reservationId) {
reservationRepository.deleteById(reservationId);
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return null;
    }
}
