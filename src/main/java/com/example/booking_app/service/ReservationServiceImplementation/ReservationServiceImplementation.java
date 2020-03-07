package com.example.booking_app.service.ReservationServiceImplementation;

import com.example.booking_app.model.Reservation;
import com.example.booking_app.repository.ReservationRepository;
import com.example.booking_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        return reservationOptional.get();
    }

    @Override
    public void update(Long reservationId, Reservation reservation) {
reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long reservationId) {
if(reservationRepository.existsById(reservationId)) {
    reservationRepository.deleteById(reservationId);
}
    }

    @Override
    public Iterable<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
