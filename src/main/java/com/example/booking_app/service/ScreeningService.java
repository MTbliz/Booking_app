package com.example.booking_app.service;

import com.example.booking_app.model.Screening;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScreeningService {
    Screening create(Screening screening);

    Screening read(Long screeningId);

    void update(Long screeningId, Screening screening);

    void delete(Long screeningId);

    Iterable<Screening> getAllScreenings();

    List<Screening>  findApplicableMoviesInRage(LocalDate date, LocalTime timeFrom, LocalTime timeTo);
}
