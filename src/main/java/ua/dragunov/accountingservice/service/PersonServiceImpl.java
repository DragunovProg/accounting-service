package ua.dragunov.accountingservice.service;

import org.springframework.stereotype.Service;
import ua.dragunov.accountingservice.model.data.PersonResponse;
import ua.dragunov.accountingservice.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<PersonResponse> findPersonById(long id) {
        return personRepository.findById(id).map(PersonResponse::fromPerson);
    }
}
