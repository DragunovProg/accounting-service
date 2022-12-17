package ua.dragunov.accountingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.dragunov.accountingservice.exceptions.AccountingServiceExceptions;
import ua.dragunov.accountingservice.model.data.PersonResponse;
import ua.dragunov.accountingservice.service.PersonService;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("{id}")
    public PersonResponse getCurrentPerson(@PathVariable long id) {

        return personService.findPersonById(id).orElseThrow(() -> AccountingServiceExceptions.personNotFound(id));
    }
}
