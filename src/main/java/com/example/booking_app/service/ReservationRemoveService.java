package com.example.booking_app.service;

import com.example.booking_app.model.CinemaSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReservationRemoveService {
    @Autowired
    CinemaSeatService cinemaSeatService;

    @Autowired
    ReservationService reservationService;

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void demoServiceMethod()
    {
        List<CinemaSeat> reservedSeats = cinemaSeatService.findReservedSeats();
        for(CinemaSeat seat: reservedSeats){
            if(seat.getReservation().getExirationDate().isBefore(LocalDateTime.now())){
                Long seatId = seat.getId();
                Long reservationId = seat.getReservation().getId();
                seat.setReservation(null);
                cinemaSeatService.update(seatId,seat);
                reservationService.delete(reservationId);
            }
        }
        System.out.println("Reserved Seats:" + reservedSeats.size());
    }
}
