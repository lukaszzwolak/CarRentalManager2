package pl.lukasz.CarRentalManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukasz.CarRentalManager.entities.CarModel;
import pl.lukasz.CarRentalManager.repositories.CarModelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    public List<CarModel> getAllModels() {
        return carModelRepository.findAll();
    }

    public CarModel getModelById(Long id) {
        Optional<CarModel> modelOptional = carModelRepository.findById(id);
        return modelOptional.orElse(null);
    }

    public CarModel saveModel(CarModel model) {
        return carModelRepository.save(model);
    }

    public void deleteModel(Long id) {
        carModelRepository.deleteById(id);
    }

    public List<CarModel> findByPrefix(String prefix) {
        return carModelRepository.findByPrefix(prefix);
    }
}
