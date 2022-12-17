package ua.dragunov.accountingservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.dragunov.accountingservice.model.Person;

import ua.dragunov.accountingservice.model.data.PersonResponse;
import ua.dragunov.accountingservice.repository.PersonRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonServiceTest {
    private PersonServiceImpl personService;
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    void testOptionalPersonIsNotEmptyWhenPersonIdIs_1_AndWhenPersonIsEmpty() {
        var presentId = 1l;
        var absentId = 2l;
        var person = new Person();

        person.setId(presentId);
        person.setFirstName("Igor");
        person.setLastName("Dragunov");
        person.setBirthday(LocalDate.of(2000, 12, 24));

        when(personRepository.findById(presentId)).thenReturn(Optional.of(person));
        when(personRepository.findById(absentId)).thenReturn(Optional.empty());

        Optional<PersonResponse> absentResponse = personService.findPersonById(absentId);

        assertThat(absentResponse).isEmpty();
        verify(personRepository).findById(absentId);

        Optional<PersonResponse> personById = personService.findPersonById(presentId);

        assertThat(personById).hasValueSatisfying(personResponse -> {
            assertThat(personResponse.firstName()).isEqualTo(person.getFirstName());
            assertThat(personResponse.lastName()).isEqualTo(person.getLastName());
            assertThat(personResponse.age()).isEqualTo(21);
        });

        verify(personRepository).findById(1l);

        verifyNoMoreInteractions(personRepository);
    }
}