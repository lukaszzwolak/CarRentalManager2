package pl.lukasz.CarRentalManager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukasz.CarRentalManager.entities.CarModel;
import pl.lukasz.CarRentalManager.services.CarModelService;

import java.util.List;

@Controller
@RequestMapping("/model")
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("models", carModelService.getAllModels());
        return "modelDirectory/model-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("carModel", new CarModel());
        return "modelDirectory/model-add";
    }

    @PostMapping("/add")
    public String add(@Valid CarModel carModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "modelDirectory/model-add";
        }
        carModelService.saveModel(carModel);
        redirectAttributes.addFlashAttribute("successMessage", "Model added successfully");
        return "redirect:/model/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        CarModel existingModel = carModelService.getModelById(id);
        model.addAttribute("carModel", existingModel);
        return "modelDirectory/model-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid CarModel carModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "modelDirectory/model-edit";
        }
        carModelService.saveModel(carModel);
        redirectAttributes.addFlashAttribute("successMessage", "Model updated successfully");
        return "redirect:/model/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        carModelService.deleteModel(id);
        redirectAttributes.addFlashAttribute("successMessage", "Model deleted successfully");
        return "redirect:/model/list";
    }

    @GetMapping("/autocomplete")
    @ResponseBody
    public List<CarModel> autocompleteModels(@RequestParam String query) {
        return carModelService.findByPrefix(query);
    }
}
