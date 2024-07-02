package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lukasz.CarRentalManager.entities.Car;
import pl.lukasz.CarRentalManager.entities.Client;
import pl.lukasz.CarRentalManager.entities.Reservation;
import pl.lukasz.CarRentalManager.services.CarService;
import pl.lukasz.CarRentalManager.services.ClientService;
import pl.lukasz.CarRentalManager.services.EmailConfirmationService;
import pl.lukasz.CarRentalManager.services.ReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;


    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
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
    public String add(@ModelAttribute("reservation") Reservation reservation) {
        Long carId = reservation.getCar().getId();
        Car car = carService.getCarById(carId);

        if (car != null) {
            reservation.setCar(car);
            reservationService.saveReservation(reservation);

            Long clientId = reservation.getClient().getId();
            Client client = clientService.getClientById(clientId);

            if (client != null) {
                emailConfirmationService.createEmailConfirmation(client);
            }

            return "redirect:/reservation/list";
        } else {
            return "redirect:/error";
        }
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("clients", clientService.getAllClients());
        return "reservationDirectory/reservation-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservation/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        reservationService.deleteReservationById(id);
        return "redirect:/reservation/list";
    }
}
