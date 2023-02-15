package com.example.person.gateway;


import com.example.person.client.AddressClient;
import com.example.person.persistence.Person;
import com.example.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PersonGateway {

    private final AddressClient addressClient;

    private final PersonRepository personRepository;

    public Person save(final Person person) {
        return personRepository.save(person);
    }

    public List<Person> listAll() {
        return personRepository.findAll();
    }

    public Person findById(final Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Impossível salvar um endereço sem relaciona-lo com" +
                        "uma pessoa! ID NULO!"));
    }
}
