package com.example.person.controller;

import com.example.person.gateway.PersonGateway;
import com.example.person.util.PersonCreator;
import com.example.person.util.PersonPostReqBodyCreator;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.example.person.util.PersonCreator.createValidPerson;
import static com.example.person.util.PersonCreator.createValidPersonReqBody;
import static com.example.person.util.PersonPostReqBodyCreator.createPersonPostReqBody;


@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @InjectMocks
    // when you want to test the class
    private PersonController personController;

    @Mock
    // every class that PersonController are using.
    private PersonGateway personGateway;


    @BeforeEach
    void setUp() {
        val personList = List.of(createValidPerson());
        BDDMockito.when(personGateway.listAll())
                .thenReturn(personList);

    }

    @Test
    void listAll_ReturnsListOfPeople() {
        val expectedName = createValidPerson().getName();
        val personList = personController.listAll();

        Assertions.assertThat(personList).isNotNull();
        Assertions.assertThat(personList.stream().toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(personList.stream().toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Display a name for your tests.")
    void findById_ReturnPersonById() {
        val expectedId = createValidPerson().getId();
        val person = personController.findById(1L);

        Assertions.assertThat(person)
                .isNotNull();

        Assertions.assertThat(person.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById returns person when successful")
    void findById_ReturnsAnime_WhenSuccessful(){
        val expectedId = createValidPerson().getId();

        val person = personController.findById(1L);

        Assertions.assertThat(person).isNotNull();

        Assertions.assertThat(person.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("save returns person when successful")
    void save_ReturnsPerson_WhenSuccessful(){
        val person = personController.save(createPersonPostReqBody());

        Assertions.assertThat(person).isEqualTo(createValidPerson());
    }
}