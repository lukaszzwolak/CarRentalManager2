package pl.lukasz.CarRentalManager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukasz.CarRentalManager.entities.Car;
import pl.lukasz.CarRentalManager.services.BrandService;
import pl.lukasz.CarRentalManager.services.CarService;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "carDirectory/car-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("brands", brandService.getAllBrands());
        return "carDirectory/car-add";
    }

    @PostMapping("/add")
    public String add(@Valid Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAllBrands());
            return "carDirectory/car-add";
        }
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car added successfully");
        return "redirect:/car/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        model.addAttribute("brands", brandService.getAllBrands());
        return "carDirectory/car-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Car car, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brands", brandService.getAllBrands());
            return "carDirectory/car-edit";
        }
        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car updated successfully");
        return "redirect:/car/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        carService.deleteCar(id);
        redirectAttributes.addFlashAttribute("successMessage", "Car deleted successfully");
        return "redirect:/car/list";
    }

    @GetMapping("/searchByBrand")
    public String searchByBrand(@RequestParam("prefix") String prefix, Model model) {
        model.addAttribute("cars", brandService.getBrandsStartingWith(prefix));
        return "carDirectory/car-list"; // Tutaj możesz użyć odpowiedniego widoku
    }
}
