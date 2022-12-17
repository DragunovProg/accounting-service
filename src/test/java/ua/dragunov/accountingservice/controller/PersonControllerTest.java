package ua.dragunov.accountingservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.dragunov.accountingservice.model.data.PersonResponse;
import ua.dragunov.accountingservice.service.PersonService;

import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerTest {
    private MockMvc mockMvc;
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = mock(PersonService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new PersonController(personService))
                .build();

    }

    @Test
    void testGivenPersonFromControllerIsExistWhenIdIs_1() throws Exception {
        var id = 1l;
        var response = new PersonResponse("Igor", "Dragunov", 21);

        when(personService.findPersonById(id)).thenReturn(Optional.of(response));

        var expectedJson = """
                {"firstName":"Igor","lastName":"Dragunov", "age": 21}
                """;

        mockMvc.perform(get("/api/v1/persons/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));

        verify(personService, only()).findPersonById(id);

    }

    @Test
    void testHttpStatusIsNotFoundWhenIdIs_2() throws Exception {
        var id = 2l;
        when(personService.findPersonById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/persons/{id}", id))
                .andExpect(status().isNotFound());

        verify(personService, only()).findPersonById(id);
    }
}