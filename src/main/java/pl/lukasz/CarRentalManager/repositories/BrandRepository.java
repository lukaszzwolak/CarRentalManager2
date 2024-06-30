package pl.lukasz.CarRentalManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.lukasz.CarRentalManager.entities.Brand;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE UPPER(b.name) LIKE CONCAT(UPPER(:prefix), '%')")
    List<Brand> findByPrefix(String prefix);
}
