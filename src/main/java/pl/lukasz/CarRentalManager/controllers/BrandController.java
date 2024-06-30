package pl.lukasz.CarRentalManager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.lukasz.CarRentalManager.entities.Brand;
import pl.lukasz.CarRentalManager.services.BrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brandDirectory/brand-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("brand", new Brand());
        return "brandDirectory/brand-add";
    }

    @PostMapping("/add")
    public String add(@Valid Brand brand, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "brandDirectory/brand-add";
        }
        brandService.saveBrand(brand);
        redirectAttributes.addFlashAttribute("successMessage", "Brand added successfully");
        return "redirect:/brand/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Brand brand = brandService.getBrandById(id);
        model.addAttribute("brand", brand);
        return "brandDirectory/brand-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid Brand brand, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "brandDirectory/brand-edit";
        }
        brandService.saveBrand(brand);
        redirectAttributes.addFlashAttribute("successMessage", "Brand updated successfully");
        return "redirect:/brand/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        brandService.deleteBrand(id);
        redirectAttributes.addFlashAttribute("successMessage", "Brand deleted successfully");
        return "redirect:/brand/list";
    }
}
