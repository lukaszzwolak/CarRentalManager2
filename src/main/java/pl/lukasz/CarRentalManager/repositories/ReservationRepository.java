package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}