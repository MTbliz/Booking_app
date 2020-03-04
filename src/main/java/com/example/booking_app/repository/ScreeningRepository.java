package com.example.booking_app.repository;

import com.example.booking_app.model.Movie;
import com.example.booking_app.model.Screening;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Long> {

    List<Screening> findByMovieTitle(Movie movie);
}

