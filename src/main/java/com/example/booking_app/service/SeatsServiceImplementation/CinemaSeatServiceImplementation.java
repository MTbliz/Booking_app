package com.example.booking_app.service.SeatsServiceImplementation;

import com.example.booking_app.model.*;
import com.example.booking_app.repository.CinemaSeatRepository;
import com.example.booking_app.service.CinemaSeatService;
import com.example.booking_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CinemaSeatServiceImplementation implements CinemaSeatService {
    @Autowired
    CinemaSeatRepository cinemaSeatRepository;

    @Autowired
    ReservationService reservationService;

    @Override
    public CinemaSeat create(CinemaSeat cinemaSeat) {
        return cinemaSeatRepository.save(cinemaSeat);
    }

    @Override
    public CinemaSeat read(Long cinemaSeatId) {
        Optional<CinemaSeat> cinemaSeatOptional = cinemaSeatRepository.findById(cinemaSeatId);
        return cinemaSeatOptional.get();
    }

    @Override
    public void update(Long cinemaSeatId, CinemaSeat cinemaSeat) {
        cinemaSeatRepository.save(cinemaSeat);
    }

    @Override
    public void delete(Long cinemaSeatId) {
if(cinemaSeatRepository.existsById(cinemaSeatId)){
    cinemaSeatRepository.deleteById(cinemaSeatId);
}
    }

    @Override
    public Iterable<CinemaSeat> getAllCinemaSeats() {
        return cinemaSeatRepository.findAll();
    }

    @Override
    public List<CinemaSeat> getCinemaSeatsByScreening(Screening screening) {
        List<CinemaSeat> cinemaSeats = cinemaSeatRepository.findByScreening(screening);
        cinemaSeats.sort(Comparator.comparing(seat -> seat.getSeatNumber()));
        cinemaSeats.sort(Comparator.comparing(seat -> seat.getSeatRow()));
        return cinemaSeats;
    }

    @Override
    public List<CinemaSeat> findReservedSeats() {
        return cinemaSeatRepository.findByReservationNotNull();
    }

    @Override
    public boolean checkIfBlankBetweenReservedSeats(List<CinemaSeat> seatsList) {
        boolean blankBetweenReservedFields = true;
        Screening screening = seatsList.get(0).getScreening();
        List<CinemaSeat> cinemaHallSeats = getCinemaSeatsByScreening(screening);
        List<CinemaSeat> temporarySeats = new ArrayList<>();
        for(CinemaSeat c: cinemaHallSeats){
            CinemaSeat artificialSeat = new CinemaSeat();
            artificialSeat.setId(c.getId());
            artificialSeat.setScreening(c.getScreening());
            artificialSeat.setReservation(c.getReservation());
            artificialSeat.setSeatNumber(c.getSeatNumber());
            artificialSeat.setSeatRow(c.getSeatRow());
            temporarySeats.add(artificialSeat);
        }

        for (CinemaSeat cs : seatsList) {
            for (CinemaSeat aSeat : temporarySeats) {
                if (cs.getId() == aSeat.getId()) {
                    aSeat.setReservation(new Reservation());
                }
            }
        }
        List<Boolean> canBeReserved = new ArrayList<>();
        for (CinemaSeat cs1 : seatsList) {


            int row = cs1.getSeatRow();
            int seatNumber = cs1.getSeatNumber();
            List<CinemaSeat> seats = temporarySeats.stream().filter(s -> s.getScreening().getCinemaHall() == cs1.getScreening().getCinemaHall()).filter(s->s.getSeatRow() == cs1.getSeatRow()).collect(Collectors.toList());
            seats.sort(Comparator.comparing(cinemaSeat -> cinemaSeat.getSeatNumber()));

                if (seats.size() >= 5) {
                    if (seatNumber > 2 && seatNumber < seats.size() - 1) {
                        for (int i = seatNumber - 1; i < seatNumber; i++) {
                            if ((seats.get(i - 2).getReservation() != null && seats.get(i - 1).getReservation() == null) || (seats.get(i + 2).getReservation() != null && seats.get(i + 1).getReservation() == null)) {
                                canBeReserved.add(false);
                            } else {
                                canBeReserved.add(true);
                            }
                        }
                    }
                    if (seatNumber <= 2) {
                        for (int i = seatNumber - 1; i < seatNumber; i++) {
                            if (seats.get(i + 1).getReservation() == null && seats.get(i + 2).getReservation() != null) {
                                canBeReserved.add(false);
                            } else {
                                canBeReserved.add(true);
                            }
                        }
                    }
                    if (seatNumber >= seats.size() - 1) {
                        for (int i = seatNumber - 1; i > seatNumber - 2; i--) {
                            if (seats.get(i - 1).getReservation() == null && seats.get(i - 2).getReservation() != null) {
                                canBeReserved.add(false);
                            } else {
                                canBeReserved.add(true);
                            }
                        }
                    }
                }
                if (seats.size() == 4) {
                    if (seatNumber <= 2) {
                        for (int i = seatNumber - 1; i < seatNumber; i++) {
                            if (seats.get(i + 1).getReservation() == null && seats.get(i + 2).getReservation() != null) {
                                canBeReserved.add(false);
                            } else {
                                canBeReserved.add(true);
                            }
                        }
                    }
                    if (seatNumber >= seats.size() - 1) {
                        for (int i = seatNumber - 1; i > seatNumber - 2; i--) {
                            if (seats.get(i - 1).getReservation() == null && seats.get(i - 2).getReservation() != null) {
                                canBeReserved.add(false);
                            } else {
                                canBeReserved.add(true);
                            }
                        }
                    }
                }
                if (seats.size() == 3) {
                    if (seatNumber == 1) {
                        if (seats.get(1).getReservation() == null && seats.get(2).getReservation() != null) {
                            canBeReserved.add(false);
                        } else {
                            canBeReserved.add(true);
                        }
                    }
                    if (seatNumber == 3) {
                        if (seats.get(0).getReservation() != null && seats.get(1).getReservation() == null) {
                            canBeReserved.add(false);
                        } else {
                            canBeReserved.add(true);
                        }
                    }
                }
             if (canBeReserved.contains(false)) {
                blankBetweenReservedFields = true;
            } else {
                blankBetweenReservedFields = false;

            }
        }

        return blankBetweenReservedFields;
    }
    @Override
    public String printReservationStatus(String firstName, String lastName, List <Long> listOfSeats, int adult, int student, int child){
        double price = adult * (ETicketType.TICKET_ADULT.getPrice()) + student * (ETicketType.TICKET_STUDENT.getPrice()) + child * (ETicketType.TICKET_CHILD.getPrice());
        List<Boolean> isReserved = new ArrayList<>();
        List<Boolean> canBeReserved = new ArrayList<>();
        boolean blankBetweenReservedFields = true;

        List<CinemaSeat> seats = new ArrayList<>();

        for (Long id : listOfSeats) {
            CinemaSeat seat = this.read(id);
            seats.add(seat);
        }
        for (CinemaSeat cinemaSeat : seats) {
            if (cinemaSeat.getReservation() == null) {
                isReserved.add(true);
            } else {
                isReserved.add(false);
            }
        }
        if (isReserved.contains(false)) {
            return "One of the seats is reserved";
        } else {
            blankBetweenReservedFields = this.checkIfBlankBetweenReservedSeats(seats);

            for (CinemaSeat cinemaSeat : seats) {
                if (LocalDateTime.now().plusMinutes(15).isAfter(cinemaSeat.getScreening().getDate())) {
                    canBeReserved.add(false);
                } else {
                    canBeReserved.add(true);
                }
            }
            if (canBeReserved.contains(false)) {
                return "You can't book seats. Seats can be booked at latest 15 minutes before the screening begins.";
            } else if(blankBetweenReservedFields==true){
                return "You can't book seats. There cannot be a single place left over in a row between two already reserved\n" +
                        "places.";
            } else {
                for (CinemaSeat seat : seats) {
                    Reservation reservation = new Reservation();
                    reservation.setExirationDate(LocalDateTime.now().plusMinutes(15));
                    reservation.setCinemaSeat(seat);
                    reservation.setFirstName(firstName);
                    reservation.setLastName(lastName);
                    reservationService.create(reservation);
                    seat.setReservation(reservation);
                    this.update(seat.getId(), seat);
                }
                return "Total amount to pay: " + price + "PLN" + "\nExpiration Time: " + LocalTime.now().plusMinutes(15).toString();
            }
        }
    }

@Override
    public boolean checkIfSeatIsReserved(CinemaSeat seat){
        if (seat.getReservation() == null) {
            return false;
        }else {
            return  true;
        }
    }
}


