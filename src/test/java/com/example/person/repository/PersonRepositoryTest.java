package com.example.person.repository;

import com.example.person.persistence.Person;
import com.example.person.util.PersonCreator;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static com.example.person.util.PersonCreator.createPersonToBeSaved;
import static com.example.person.util.PersonCreator.createValidPerson;


@DataJpaTest
@DisplayName("Person Repository Tests")
class PersonRepositoryTest {

    private final PersonRepository personRepository;

    PersonRepositoryTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    void save_PersistPerson_When_Successfull() {
        val personToBeSaved = createPersonToBeSaved();
        val savedPerson = this.personRepository.save(createPersonToBeSaved());

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getId()).isNotNull();
        Assertions.assertThat(savedPerson.getName()).isEqualTo(personToBeSaved.getName());

    }


    @Test
    void save_UpdatePersistPerson_When_Successfull() {
        val personToBeSaved = createPersonToBeSaved();
        val savedPerson = this.personRepository.save(createPersonToBeSaved());

        savedPerson.setName("Different name been set.");

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getId()).isNotNull();
        Assertions.assertThat(savedPerson.getName()).isEqualTo(personToBeSaved.getName());

    }

    @Test
    void delete_Person_When_Successfull() {
        val createPersonToBeSaved = createValidPerson();
        val savedPerson = this.personRepository.save(createPersonToBeSaved);
        this.personRepository.delete(savedPerson);
        Optional<Person> personOptional = this.personRepository.findById(savedPerson.getId());
        Assertions.assertThat(personOptional).isEmpty();
    }

    @Test
    void list_AllPersons_When_Successfull() {
        val personToBeSaved = createPersonToBeSaved();
        val personOptional = this.personRepository.findAll();

        Assertions.assertThat(personOptional).isNotNull();
        Assertions.assertThat(personOptional).isNotEmpty();
        Assertions.assertThat(personToBeSaved.getId()).isEqualTo(personOptional);
    }
}