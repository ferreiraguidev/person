package com.example.person.controller;


import com.example.person.persistence.Person;
import com.example.person.repository.PersonRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import java.util.List;

import static com.example.person.util.PersonPostReqBodyCreator.createPersonPostReqBody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PersonControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonRepository personRepository;


    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }


    @Test
    void should_Save_When_Successfull() {
        val person = createPersonPostReqBody();
        val newRequest = new HttpEntity<>(person, null);

        val exchange = testRestTemplate.postForEntity("/person/save", newRequest, Person.class);

        assertEquals(201, exchange.getStatusCode().value());
        assertEquals(person.getName(), exchange.getBody().getName());
        // more assertions * coverage.
    }

    @Disabled
    @Test
    void should_FindById_When_Successfull() {
        val person = Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();

        val savedPerson = personRepository.save(person);

        val exchange = testRestTemplate.exchange("/person/" + savedPerson.getId(),
                GET, null, Person.class);

        assertEquals(200, exchange.getStatusCode().value());

    }

    @Test
    void should_ListAll_When_Successfull() {
        val person = Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();

        val personTwo = Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();

        val people = personRepository.saveAll(List.of(person, personTwo));

//        val savedPerson = personRepository.findAll(person, personTwo);

        val exchange = testRestTemplate.exchange("/person/" + people.toArray(),
                GET, null, Person.class);

        assertEquals(200, exchange.getStatusCode().value());

    }

}
