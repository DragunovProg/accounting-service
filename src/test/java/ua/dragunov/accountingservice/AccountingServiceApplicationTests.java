package ua.dragunov.accountingservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ua.dragunov.accountingservice.model.Person;
import ua.dragunov.accountingservice.repository.PersonRepository;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AccountingServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties"
)
class AccountingServiceApplicationTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    private PersonRepository repository;

    @Test
    void testAccountingFunctionalityE2E() throws Exception {
        Person person = new Person();

        person.setFirstName("Igor");
        person.setLastName("Dragunov");
        person.setBirthday(LocalDate.of(2000, 12, 24));

        repository.save(person);

        mvc.perform(get("/api/v1/persons/{id}", person.getId()).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName", is("Igor")))
                .andExpect(jsonPath("$.lastName", is("Dragunov")))
                .andExpect(jsonPath("$.age", is(21)));



    }

}
