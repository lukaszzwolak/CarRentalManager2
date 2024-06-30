package pl.lukasz.CarRentalManager.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.repositories.*;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}