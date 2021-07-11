package emergencysystem.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import emergencysystem.controller.PersonController;
import emergencysystem.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonReadService personReadService;
    @MockBean
    private PersonUpdateService personUpdateService;
    @MockBean
    private PersonDeletionService personDeletionService;
    @MockBean
    private PersonCreationService personCreationService;
    @MockBean
    private FireStationCreationService fireStationCreationService;
    @MockBean
    private MedicalRecordCreationService medicalRecordCreationService;

    @Test
    public void shouldGetPersons() throws Exception {

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Harry");
        person.setLastName("POTTER");
        person.setAddress("4, Privet Drive");
        person.setCity("Little Whinging");
        person.setZip("21944");
        person.setPhone("+44 20 7234 3456");
        person.setEmail("hpotter@mail.co.uk");

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        Mockito.when(personReadService.getPersons()).thenReturn(persons);

        MvcResult mvcResult = mockMvc.perform(get("/persons")).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(persons);

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void shouldGetPersonById() throws Exception {

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Harry");
        person.setLastName("POTTER");
        person.setAddress("4, Privet Drive");
        person.setCity("Little Whinging");
        person.setZip("21944");
        person.setPhone("+44 20 7234 3456");
        person.setEmail("hpotter@mail.co.uk");

        Mockito.when(personReadService.getPersonById(1L)).thenReturn(person);

        MvcResult mvcResult = mockMvc.perform(get("/persons/1")).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(person);

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void shouldCreatePerson() throws Exception {

        Person person = new Person();
        person.setFirstName("Harry");
        person.setLastName("POTTER");
        person.setAddress("4, Privet Drive");
        person.setCity("Little Whinging");
        person.setZip("21944");
        person.setPhone("+44 20 7234 3456");
        person.setEmail("hpotter@mail.co.uk");

        Person personSaved = new Person();
        personSaved.setId(1L);
        personSaved.setFirstName("Harry");
        personSaved.setLastName("POTTER");
        personSaved.setAddress("4, Privet Drive");
        personSaved.setCity("Little Whinging");
        personSaved.setZip("21944");
        personSaved.setPhone("+44 20 7234 3456");
        personSaved.setEmail("hpotter@mail.co.uk");

        Mockito.when(personCreationService.createPerson(person)).thenReturn(personSaved);

        mockMvc.perform(post("/createPerson")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(person))
        ).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(personSaved))).andReturn();
    }

    @Test
    public void shouldCreatePersons() throws Exception {

        Person person = new Person();
        person.setFirstName("Harry");
        person.setLastName("POTTER");
        person.setAddress("4, Privet Drive");
        person.setCity("Little Whinging");
        person.setZip("21944");
        person.setPhone("+44 20 7234 3456");
        person.setEmail("hpotter@mail.co.uk");

        List<Person> persons = new ArrayList<>();
        persons.add(person);

        Person personSaved = new Person();
        personSaved.setId(1L);
        personSaved.setFirstName("Harry");
        personSaved.setLastName("POTTER");
        personSaved.setAddress("4, Privet Drive");
        personSaved.setCity("Little Whinging");
        personSaved.setZip("21944");
        personSaved.setPhone("+44 20 7234 3456");
        personSaved.setEmail("hpotter@mail.co.uk");

        List<Person> personsSaved = new ArrayList<>();
        personsSaved.add(personSaved);

        Mockito.when(personCreationService.createPersons(persons)).thenReturn(personsSaved);

        mockMvc.perform(post("/createPersons")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(persons))
        ).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(personsSaved))).andReturn();
    }

    @Test
    public void shouldUpdatePerson() throws Exception {

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Harry");
        person.setLastName("POTTER");
        person.setAddress("4, Privet Drive");
        person.setCity("Little Whinging");
        person.setZip("21944");
        person.setPhone("+44 20 7234 3456");
        person.setEmail("hpotter@mail.co.uk");

        Person personUpdated = new Person();
        personUpdated.setId(1L);
        personUpdated.setFirstName("Vernon");
        personUpdated.setLastName("DURSLEY");
        personUpdated.setAddress("4, Privet Drive");
        personUpdated.setCity("Little Whinging");
        personUpdated.setZip("21944");
        personUpdated.setPhone("+44 20 7234 3456");
        personUpdated.setEmail("vdursley@mail.co.uk");

        Mockito.when(personUpdateService.updatePerson(person)).thenReturn(personUpdated);

        mockMvc.perform(put("/updatePerson")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(personUpdated)));
    }

    @Test
    public void shouldDeletePerson() throws Exception {

        Mockito.doNothing().when(personDeletionService).deletePersonById(1L);

        mockMvc.perform(delete("/persons/1")).andExpect(status().isOk());

        Mockito.verify(personDeletionService, Mockito.times(1)).deletePersonById(1L);
    }
}
