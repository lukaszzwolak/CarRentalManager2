package pl.lukasz.CarRentalManager.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import pl.lukasz.CarRentalManager.entities.*;
import pl.lukasz.CarRentalManager.services.*;

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
    public String add(Model model) {
        model.addAttribute("client", new Client());
        return "clientDirectory/client-add";
    }

    @PostMapping("/add")
    public String add(Client client) {
        service.saveClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Client client = service.getClientById(id);
        model.addAttribute("client", client);
        return "clientDirectory/client-edit";
    }

    @PostMapping("/edit")
    public String edit(Client client) {
        service.saveClient(client);
        return "redirect:/client/list";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id) {
        service.deleteClientById(id);
        return "redirect:/client/list";
    }
}