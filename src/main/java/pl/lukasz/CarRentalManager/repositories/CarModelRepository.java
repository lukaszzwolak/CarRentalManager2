package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.lukasz.CarRentalManager.entities.CarModel;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    @Query("SELECT b FROM CarModel b WHERE UPPER(b.name) LIKE CONCAT(UPPER(:prefix), '%')")
    List<CarModel> findByPrefix(String prefix);
}
