package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.services.*;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("reservations", service.getAllReservations());
        return "reservationDirectory/reservation-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("cars", carService.getAllCars());
        return "reservationDirectory/reservation-add";
    }

    @PostMapping("/add")
    public String add(Reservation reservation) {
        service.saveReservation(reservation);
        return "redirect:/reservation/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Reservation reservation = service.getReservationById(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("cars", carService.getAllCars());
        return "reservationDirectory/reservation-edit";
    }

    @PostMapping("/edit")
    public String edit(Reservation reservation) {
        service.saveReservation(reservation);
        return "redirect:/reservation/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        service.deleteReservationById(id);
        return "redirect:/reservation/list";
    }
}