package pl.lukasz.CarRentalManager.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.repositories.*;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}