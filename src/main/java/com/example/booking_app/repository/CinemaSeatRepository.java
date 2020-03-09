package com.example.booking_app.repository;

import com.example.booking_app.model.CinemaHall;
import com.example.booking_app.model.CinemaSeat;
import com.example.booking_app.model.Screening;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaSeatRepository extends CrudRepository <CinemaSeat,Long> {
    List<CinemaSeat> findByScreening(Screening screening);

    List<CinemaSeat> findByReservationNotNull();

    List<CinemaSeat> findBySeatRow(int seatRow);
}
