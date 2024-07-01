package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandId(Long brandId);
}