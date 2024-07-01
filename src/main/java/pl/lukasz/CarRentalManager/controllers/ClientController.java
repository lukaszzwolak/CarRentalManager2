package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.lukasz.CarRentalManager.entities.Client;
import pl.lukasz.CarRentalManager.services.ClientService;
import pl.lukasz.CarRentalManager.services.EmailConfirmationService;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private EmailConfirmationService emailConfirmationService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("clients", service.getAllClients());
        return "clientDirectory/client-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        return "clientDirectory/client-add";
    }

    @PostMapping("/addClient")
    public String addClient(@ModelAttribute("client") Client client) {
        service.saveClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Client client = service.getClientById(id);
        model.addAttribute("client", client);
        return "clientDirectory/client-edit";
    }

    @PostMapping("/editClient")
    public String editClient(@ModelAttribute("client") Client client) {
        service.saveClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/remove/{id}")
    public String removeClient(@PathVariable("id") Long id) {
        service.deleteClientById(id);
        return "redirect:/client/list";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "clientDirectory/client-register";
    }

    @PostMapping("/registerClient")
    public String registerClient(@ModelAttribute("client") Client client) {
        // Save client details (excluding email confirmation status) to database
        service.saveClient(client);

        // Generate email confirmation and send email
        emailConfirmationService.createEmailConfirmation(client);

        return "redirect:/client/registration-success";
    }

    @GetMapping("/registration-success")
    public String registrationSuccess() {
        return "clientDirectory/client-registration-success";
    }

    @GetMapping("/confirm-email")
    public String confirmEmail(@RequestParam("code") String confirmationCode, Model model) {
        try {
            emailConfirmationService.confirmEmail(confirmationCode);
            model.addAttribute("message", "Email confirmed successfully.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid or expired confirmation code.");
        }
        return "clientDirectory/client-email-confirmation-result";
    }
}
