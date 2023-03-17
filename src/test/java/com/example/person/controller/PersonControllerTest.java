package com.example.person.controller;


import com.example.person.persistence.Person;
import com.example.person.repository.PersonRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
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
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

//@WireMockTest(httpPort = 8080)
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
        // arrange.
        val person = createPersonPostReqBody();
        val newRequest = new HttpEntity<>(person, null);

        // act
        val exchange = testRestTemplate.postForEntity("/person/save", newRequest, Person.class);

        // assert
        assertEquals(201, exchange.getStatusCode().value());
        assertEquals(person.getName(), exchange.getBody().getName());
        // more assertions * coverage.
    }


    @Test
    void should_FindById_When_Successfull() {
        //1 arrange *

        val person = Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();

        val savedPerson = personRepository.save(person);

        //act *
        val exchange = testRestTemplate.exchange("/person/" + savedPerson.getId(),
                GET, null, Person.class);

        // assert *
        assertEquals(200, exchange.getStatusCode().value());

    }

    @Test
    void should_ListAll_When_Successfull() {
        // 1 arrange.
        val person = Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();

        val personTwo = Person.builder()
                .cpf("11665985")
                .rg("57899")
                .email("fgfasdsdaff@gmail.com")
                .name("krokvqqqq")
                .build();

       personRepository.saveAll(List.of(person, personTwo));

       // act * action
        val exchange = testRestTemplate.exchange("/person/all",
                GET, null, String.class);

        // assert *
        assertEquals(200, exchange.getStatusCode().value());

    }
}
