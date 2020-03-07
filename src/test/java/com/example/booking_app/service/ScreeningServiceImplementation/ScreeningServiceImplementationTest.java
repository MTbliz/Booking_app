package com.example.booking_app.service.ScreeningServiceImplementation;

import com.example.booking_app.model.*;
import com.example.booking_app.repository.ScreeningRepository;
import com.example.booking_app.service.ScreeningService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ScreeningServiceImplementationTest {

    @Autowired
    ScreeningService screeningService;

    @MockBean
    ScreeningRepository screeningRepository;

    @Test
    void createTest() {
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        when(screeningRepository.save(screening)).thenReturn(screening);
        Assertions.assertEquals(screening,screeningService.create(screening));
    }

    @Test
    void readTest() {
        long id = 1;
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        screening.setId((long)1);
        when(screeningRepository.findById(id))
                .thenReturn(Optional.of(screening));
        Assertions.assertEquals((long)1, screeningService.read(id).getId());
    }

    @Test
    void updateTest() {
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        when(screeningRepository.save(screening)).thenReturn(screening);
        Assertions.assertEquals(screening,screeningService.create(screening));
    }

    @Test
    void deleteByIdTest() {
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        screening.setId((long)1);
        screeningService.delete(screening.getId());
        verify(screeningRepository,times(1)).existsById(screening.getId());
    }

    @Test
    void getAllScreeningsTest() {
        List<Screening> screenings = new ArrayList<>();
        Screening screening = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        screenings.add(screening);
        Iterable<Screening> iterable = screenings;
        when(screeningRepository.findAll()).thenReturn(iterable);
        Assertions.assertEquals(1,((List<Screening>) screeningService.getAllScreenings()).size());
    }

    @Test
    void findApplicableMoviesInRageTest() {
        Screening screening1 = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        screening1.setId((long)1);
        Screening screening2 = new Screening(new Movie(), LocalDateTime.now(),new CinemaHall());
        screening2.setId((long)2);
        List<Screening> screenings = new ArrayList<>();
        screenings.add(screening1);
        screenings.add(screening2);
        Iterable<Screening> iterable = screenings;
        LocalDate localDate = LocalDate.now();
        LocalTime timeFrom = LocalTime.now();
        LocalTime timeTo = LocalTime.now();

        when(screeningService.getAllScreenings()).thenReturn(iterable);
        Assertions.assertEquals(2, ((List<Screening>) screeningService.getAllScreenings()).size());
    }
}