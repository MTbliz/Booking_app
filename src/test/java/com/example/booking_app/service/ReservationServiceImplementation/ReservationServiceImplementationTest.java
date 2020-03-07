package com.example.booking_app.service.ReservationServiceImplementation;

import com.example.booking_app.model.*;
import com.example.booking_app.repository.ReservationRepository;
import com.example.booking_app.service.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReservationServiceImplementationTest {

    @Autowired
    ReservationService reservationService;

    @MockBean
    ReservationRepository reservationRepository;

    @Test
    void create() {
        Reservation reservation = new Reservation("Jan","Kowalski",LocalDateTime.now(), new CinemaSeat());
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Assertions.assertEquals(reservation,reservationService.create(reservation));
    }

    @Test
    void readTest() {
        long id = 1;
        Reservation reservation = new Reservation("Jan","Kowalski",LocalDateTime.now(), new CinemaSeat());
        reservation.setId((long)1);
        when(reservationRepository.findById(id))
                .thenReturn(Optional.of(reservation));
        Assertions.assertEquals((long)1, reservationService.read(id).getId());
    }

    @Test
    void updateTest() {
        Reservation reservation = new Reservation("Jan","Kowalski",LocalDateTime.now(), new CinemaSeat());
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Assertions.assertEquals(reservation,reservationService.create(reservation));
    }

    @Test
    void deleteByIdTest() {
        Reservation reservation = new Reservation("Jan","Kowalski",LocalDateTime.now(), new CinemaSeat());
        reservation.setId((long)1);
        reservationService.delete(reservation.getId());
        verify(reservationRepository,times(1)).existsById(reservation.getId());
    }

    @Test
    void getAllReservationsTest() {
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservation = new Reservation("Jan","Kowalski",LocalDateTime.now(), new CinemaSeat());
        reservations.add(reservation);
        Iterable<Reservation> iterable = reservations;
        when(reservationRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1,((List<Reservation>) reservationService.getAllReservations()).size());
    }
}