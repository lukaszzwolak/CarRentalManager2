package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasz.CarRentalManager.entities.EmailConfirmation;

public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {

}
