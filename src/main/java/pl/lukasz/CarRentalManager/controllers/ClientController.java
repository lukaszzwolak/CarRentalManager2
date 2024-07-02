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

    @PostMapping("/add")
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

    @PostMapping("/edit")
    public String editClient(@ModelAttribute("client") Client client) {
        service.saveClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/remove/{id}")
    public String removeClient(@PathVariable("id") Long id) {
        service.deleteClientById(id);
        return "redirect:/client/list";
    }
}
