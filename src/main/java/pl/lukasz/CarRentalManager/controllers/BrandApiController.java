package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasz.CarRentalManager.entities.Brand;
import pl.lukasz.CarRentalManager.services.BrandService;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandApiController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/autocomplete")
    public List<Brand> autocomplete(@RequestParam("query") String query) {
        return brandService.findByPrefix(query);
    }
}
