package pl.lukasz.CarRentalManager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.services.*;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("cars", service.getAllCars());
        return "carDirectory/car-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        return "carDirectory/car-add";
    }

    //redirect(przekierowanie)
    //BindingResult interfejs Springa(przechowuje wyniki wlidacji obiektu @Valid)
    //RedirectAttributes interfejs Springa(przekazuje atrybuty flash)
    @PostMapping("/add")
    public String add(@Valid Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "carDirectory/car-add";
        }
        service.saveCar(car);
        //addFlashAttribute dodaje atrybut flash o nazwie successMessage z wartością Car added successfully
        redirectAttributes.addFlashAttribute("successMessage", "Car added successfully");
        return "redirect:/car/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Car car = service.getCarById(id);
        model.addAttribute("car", car);
        return "carDirectory/car-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "carDirectory/car-edit";
        }
        service.saveCar(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car updated successfully");
        return "redirect:/car/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        service.deleteCar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Car deleted successfully");
        return "redirect:/car/list";
    }
}
