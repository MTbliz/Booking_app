package com.example.booking_app.service.SeatsServiceImplementation;

import com.example.booking_app.model.*;
import com.example.booking_app.repository.CinemaSeatRepository;
import com.example.booking_app.service.CinemaSeatService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
class CinemaSeatServiceImplementationTest {

    @Autowired
    private CinemaSeatService cinemaSeatService;

    @MockBean
    private CinemaSeatRepository cinemaSeatRepository;

    @Test
    void createTest() {
        CinemaSeat cinemaSeat = new CinemaSeat(1,1,new Screening(),new Reservation());
        when(cinemaSeatRepository.save(cinemaSeat)).thenReturn(cinemaSeat);
        Assertions.assertEquals(cinemaSeat, cinemaSeatService.create(cinemaSeat));
    }

    @Test
    void readTest() {
        long id = 1;
        when(cinemaSeatRepository.findById(id))
                .thenReturn(Optional.of(new CinemaSeat(1, 1, new Screening(), new Reservation())));
        Assertions.assertEquals(1, cinemaSeatService.read(id).getSeatNumber());
        Assertions.assertEquals(1, cinemaSeatService.read(id).getSeatRow());
    }

    @Test
    void updateTest() {
        CinemaSeat cinemaSeat = new CinemaSeat(1,1,new Screening(),new Reservation());
        when(cinemaSeatRepository.save(cinemaSeat)).thenReturn(cinemaSeat);
        Assertions.assertEquals(cinemaSeat, cinemaSeatService.create(cinemaSeat));
    }

    @Test
    void deleteByIdTest() {
       CinemaSeat cinemaSeat = new CinemaSeat(1,1,new Screening(),new Reservation());
       cinemaSeat.setId((long) 1);
       cinemaSeatService.delete(cinemaSeat.getId());
       verify(cinemaSeatRepository,times(1)).existsById(cinemaSeat.getId());
    }

    @Test
    void getAllCinemaSeatsTest() {
        List<CinemaSeat> seats = new ArrayList<>();
        CinemaSeat cinemaSeat = new CinemaSeat(1,1,new Screening(), new Reservation());
        seats.add(cinemaSeat);
        Iterable<CinemaSeat> iterable = seats;
        when(cinemaSeatRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1,((List<CinemaSeat>) cinemaSeatService.getAllCinemaSeats()).size());
    }

    @Test
    void getCinemaSeatsByScreeningTest() {
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        when(cinemaSeatRepository.findByScreening(screening))
                .thenReturn(Stream.of(new CinemaSeat(1,1,new Screening(), new Reservation())).collect(Collectors.toList()));
        Assertions.assertEquals(1, cinemaSeatService.getCinemaSeatsByScreening(screening).size());
    }

    @Test
    void findReservedSeatsTest() {
        List<CinemaSeat> seats = new ArrayList<>();
        CinemaSeat cinemaSeat = new CinemaSeat(1,1,new Screening(), new Reservation());
        seats.add(cinemaSeat);
        when(cinemaSeatRepository.findAll()).thenReturn(seats);
        Assertions.assertEquals(1,seats.size());
    }

    @Test
    void findAllSeatsInHallTest() {
        CinemaHall cinemaHall = new CinemaHall();
        when(cinemaSeatRepository.findByScreeningCinemaHall(cinemaHall))
                .thenReturn(Stream.of(new CinemaSeat(1,1,new Screening(), new Reservation())).collect(Collectors.toList()));
        Assertions.assertEquals(1, cinemaSeatService.findAllSeatsInHall(cinemaHall).size());
    }

    @Test
    void checkIfBlankBetweenReservedSeatsTest() {
        //given
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setId((long) 1);
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),cinemaHall);
        CinemaSeat cinemaSeat1 = new CinemaSeat(1,1,screening, null);
        cinemaSeat1.setId((long) 1);
        CinemaSeat cinemaSeat2 = new CinemaSeat(2,1,screening, null);
        cinemaSeat2.setId((long) 2);
        CinemaSeat cinemaSeat3 = new CinemaSeat(3,1,screening, null);
        cinemaSeat3.setId((long) 3);
        CinemaSeat cinemaSeat4 = new CinemaSeat(4,1,screening, null);
        cinemaSeat3.setId((long) 4);
        CinemaSeat cinemaSeat5 = new CinemaSeat(5,1,screening, null);
        cinemaSeat3.setId((long) 5);

        List<CinemaSeat> seatsList1 = new ArrayList<>();
        seatsList1.add(cinemaSeat1);
        seatsList1.add(cinemaSeat2);

        List<CinemaSeat> seatsList2 = new ArrayList<>();
        seatsList2.add(cinemaSeat1);
        seatsList2.add(cinemaSeat3);

        List<CinemaSeat> seatsList3 = new ArrayList<>();
        seatsList3.add(cinemaSeat1);
        seatsList3.add(cinemaSeat2);
        seatsList3.add(cinemaSeat3);
        seatsList3.add(cinemaSeat4);
        seatsList3.add(cinemaSeat5);


        //when
        when(cinemaSeatService.findAllSeatsInHall(cinemaHall))
                .thenReturn(seatsList3);
        boolean result1 = cinemaSeatService.checkIfBlankBetweenReservedSeats(seatsList1);
        boolean result2 = cinemaSeatService.checkIfBlankBetweenReservedSeats(seatsList2);

        //then
        Assertions.assertEquals(false,result1);
        Assertions.assertEquals(true,result2);
    }

    @Test
    void checkIfSeatIsReserved() {
        //given
        CinemaSeat cinemaSeat1 = new CinemaSeat(1,1, new Screening(), null);
        CinemaSeat cinemaSeat2 = new CinemaSeat(1,1,new Screening(), new Reservation());

        //when
      boolean  result1 = cinemaSeatService.checkIfSeatIsReserved(cinemaSeat1);
      boolean  result2 = cinemaSeatService.checkIfSeatIsReserved(cinemaSeat2);

        //then
      Assertions.assertEquals(false,result1);
      Assertions.assertEquals(true,result2);
    }
}