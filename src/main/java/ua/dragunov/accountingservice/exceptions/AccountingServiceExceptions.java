package ua.dragunov.accountingservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccountingServiceExceptions {

    private AccountingServiceExceptions() {}

    public static ResponseStatusException personNotFound(long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Person with id %d is not exist", id));
    }

}
