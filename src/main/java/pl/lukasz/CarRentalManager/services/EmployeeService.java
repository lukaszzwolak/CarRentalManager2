package pl.lukasz.CarRentalManager.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.repositories.*;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}