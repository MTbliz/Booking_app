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
             jdbcTemplate.update(
                "INSERT INTO cinema_hall(id_cinemahall) VALUES (1);\n" +
                    "INSERT INTO cinema_hall(id_cinemahall) VALUES (2);\n" +
                    "INSERT INTO cinema_hall(id_cinemahall) VALUES (3);\n" +

                    "INSERT INTO movie(id_movie,title) VALUES (1,'Witcher');\n" +
                    "INSERT INTO movie(id_movie,title) VALUES (2,'Shrek');\n" +
                    "INSERT INTO movie(id_movie,title) VALUES (3,'Mamma Mia');\n" +

                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (1,'2020-04-25 18:00:00',1,1);\n" +
                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (2,'2020-04-25 19:00:00',1,2);\n" +
                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (3,'2020-04-25 20:00:00',1,3);\n" +
                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (4,'2020-04-25 21:00:00',2,3);\n" +
                    "INSERT INTO screening(id_screening,date,id_cinemahall,id_movie) Values (5,'2020-02-25 21:00:00',3,2);\n" +


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
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (16,8,2,1);\n" +

                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (17,1,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (18,2,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (19,3,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (20,4,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (21,5,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (22,6,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (23,7,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (24,8,1,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (25,1,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (26,2,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (27,3,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (28,4,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (29,5,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (30,6,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (31,7,2,2);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (32,8,2,2);\n" +

                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (33,1,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (34,2,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (35,3,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (36,4,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (37,5,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (38,6,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (39,7,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (40,8,1,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (41,1,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (42,2,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (43,3,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (44,4,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (45,5,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (46,6,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (47,7,2,3);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (48,8,2,3);\n" +

                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (49,1,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (50,2,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (51,3,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (52,4,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (53,5,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (54,6,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (55,7,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (56,8,1,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (57,1,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (58,2,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (59,3,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (60,4,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (61,5,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (62,6,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (63,7,2,4);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (64,8,2,4);\n" +

                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (65,1,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (66,2,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (67,3,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (68,4,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (69,5,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (70,6,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (71,7,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (72,8,1,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (73,1,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (74,2,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (75,3,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (76,4,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (77,5,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (78,6,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (79,7,2,5);\n" +
                    "INSERT INTO cinema_seat(id_seat,seat_number,seat_row,id_screening) VALUES (80,8,2,5);\n"
             );
             return "Records added to database";
        } else {
            return "You can't add records to database. Records already added";
        }
    }
}
