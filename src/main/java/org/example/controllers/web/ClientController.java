package org.example.controllers.web;

import lombok.RequiredArgsConstructor;
import org.example.dtos.ClientDto;
import org.example.exceptions.InvalidDataException;
import org.example.mappers.ClientMapper;
import org.example.services.clientservice.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientMapper.toDtoList(clientService.getAllClients()));
        return "clients";
    }

    @GetMapping(value = {"/clients/{id}", "/clients/add"})
    public String singleClient(@PathVariable(required = false) Long id, Model model) {
        ClientDto client = new ClientDto();
        if (id != null) {
            client.setId(id);
        }
        model.addAttribute("selectedClient", client);
        return "change-client";
    }

    @PostMapping("/clients/change")
    public String changeClient(@Valid @ModelAttribute("selectedClient") ClientDto clientDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(
                    result.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((s, s2) -> s + " " + s2).orElse("Ошибка в обработке данных, обратитесь в службу поддержки сайта!")
            );
        }
        if (clientDto.getId() == 0L) {
            clientService.addClient(clientMapper.toEntity(clientDto));
        } else {
            clientService.updateClient(clientMapper.toEntity(clientDto), clientDto.getId());
        }
        return "redirect:/library";
    }
}
