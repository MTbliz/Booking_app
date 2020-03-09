package com.example.booking_app.contoller;

import com.example.booking_app.model.*;
import com.example.booking_app.service.CinemaSeatService;
import com.example.booking_app.service.ReservationService;
import com.example.booking_app.service.ScreeningService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class CinemaController {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @Autowired
    ScreeningService screeningService;

    @Autowired
    CinemaSeatService cinemaSeatService;

    @Autowired
    ReservationService reservationService;

@RequestMapping("/screenings")
public @ResponseBody List<Screening>  getScreenings(@RequestParam(value = "screeningDateTime") String dateTime) throws JsonProcessingException {
    ScreeningDateTime screeningDateTime = null;

        screeningDateTime = new ObjectMapper().setDateFormat(simpleDateFormat).readValue(dateTime, ScreeningDateTime.class);

    LocalDate date = screeningDateTime.getDate();
    LocalTime timeFrom = screeningDateTime.getTimeFrom();
    LocalTime timeTo = screeningDateTime.getTimeTo();
    List<Screening> applicableMovies = screeningService.findApplicableMoviesInRage(date,timeFrom,timeTo);
    return applicableMovies;
    }

@RequestMapping("/availableseats")
public @ResponseBody List<CinemaSeat> showAvailableSeats(@RequestParam(value = "screeningDateTime") String dateTime ,@RequestParam(value="id") Long id)  {
    Screening screening = screeningService.read(id);
    List<CinemaSeat> seats = cinemaSeatService.getCinemaSeatsByScreening(screening);
    List<CinemaSeat> availableSeats = new ArrayList<>();
   for(CinemaSeat seat: seats){
       if ((seat.getReservation()==null))
           availableSeats.add(seat);
   }
    return  availableSeats;
}
@PostMapping("/cinemasummary")
public @ResponseBody String getTotalCost(@Valid @RequestBody  ReservationData reservationData, Errors errors){
if(errors.hasErrors()){
    return errors.getFieldError().getDefaultMessage();
}
    String reservationStatus = cinemaSeatService.printReservationStatus(reservationData.getUser().getFirstName(),
                                                                        reservationData.getUser().getLastName(),
                                                                        reservationData.getListOfSeats(),
                                                                        reservationData.getAdult(),
                                                                        reservationData.getStudent(),
                                                                        reservationData.getChild());
        return reservationStatus;
}


}
