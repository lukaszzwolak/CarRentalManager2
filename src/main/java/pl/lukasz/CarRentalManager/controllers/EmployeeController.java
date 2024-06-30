package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.services.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employeeDirectory/employee-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeDirectory/employee-add";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employeeDirectory/employee-edit";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        service.saveEmployee(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        service.deleteEmployeeById(id);
        return "redirect:/employee/list";
    }
}