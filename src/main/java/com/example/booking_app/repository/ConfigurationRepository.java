package com.example.booking_app.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ConfigurationRepository  {

    @Autowired
    CinemaSeatRepository cinemaSeatRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  ConfigurationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Transactional
    public String insertInitialData() {
        if (!cinemaSeatRepository.findAll().iterator().hasNext() && !screeningRepository.findAll().iterator().hasNext()) {
             jdbcTemplate.update("INSERT INTO cinema_hall(id_cinemahall) VALUES (1);\n" +
                    "INSERT INTO cinema_hall(id_cinemahall) VALUES (2);\n" +

                    "INSERT INTO movie(id_movie,title) VALUES (1,'Witcher');\n" +
                    "INSERT INTO movie(id_movie,title) VALUES (2,'Shrek');\n" +

                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (1,'2020-03-25 18:00:00',1,1);\n" +
                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (2,'2020-02-25 21:00:00',1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (1,1,1,1);\n" +

                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (2,2,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (3,3,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (4,4,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (5,5,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (6,6,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (7,7,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (8,8,1,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (9,1,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (10,2,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (11,3,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (12,4,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (13,5,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (14,6,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (15,7,2,1);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (16,8,2,1);\n");
             return "Records added to database";
        } else {
            return "You can't add records to database. Records already added";
        }
    }
}
