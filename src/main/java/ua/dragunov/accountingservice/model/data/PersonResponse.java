package ua.dragunov.accountingservice.model.data;

import ua.dragunov.accountingservice.model.Person;

public record PersonResponse(
        String firstName,
        String lastName,
        int age) {

    public static PersonResponse fromPerson(Person person) {
        return new PersonResponse(
                person.getFirstName(),
                person.getLastName(),
                person.getAge()
        );
    }
}
