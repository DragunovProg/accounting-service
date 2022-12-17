package ua.dragunov.accountingservice.service;

import ua.dragunov.accountingservice.model.data.PersonResponse;

import java.util.Optional;

public interface PersonService {

    Optional<PersonResponse> findPersonById(long id);


}
