package com.example.booking_app.service.ScreeningServiceImplementation;

import com.example.booking_app.model.Screening;
import com.example.booking_app.repository.ScreeningRepository;
import com.example.booking_app.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImplementation implements ScreeningService {
    @Autowired
    ScreeningRepository screeningRepository;

    @Override
    public Screening create(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public Screening read(Long screeningId) {
        Optional<Screening> screeningOptional = screeningRepository.findById(screeningId);
        return screeningOptional.get() ;
    }

    @Override
    public void update(Long screeningId, Screening screening) { screeningRepository.save(screening);

    }

    @Override
    public void delete(Long screeningId) {
        if(screeningRepository.existsById(screeningId)){
            screeningRepository.deleteById(screeningId);
        }
    }

    @Override
    public Iterable<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    @Override
    public List<Screening> findApplicableMoviesInRage(LocalDate date, LocalTime timeFrom, LocalTime timeTo){
        List<Screening> screenings = (List<Screening>) this.getAllScreenings();
        List<Screening>  applicableMovies = screenings.stream().filter(screening -> screening.getDate().toLocalDate().equals(date))
                .filter(screening -> screening.getDate().toLocalTime().isAfter(timeFrom) || screening.getDate().toLocalTime().equals(timeFrom) )
                .filter(screening -> screening.getDate().toLocalTime().isBefore(timeTo) || screening.getDate().toLocalTime().equals(timeTo))
                .collect(Collectors.toList());
        applicableMovies.sort(Comparator.comparing(screening -> screening.getDate().toLocalTime()));
        applicableMovies.sort(Comparator.comparing(screening -> screening.getMovie().getTitle()));
        return applicableMovies;
    }
}
