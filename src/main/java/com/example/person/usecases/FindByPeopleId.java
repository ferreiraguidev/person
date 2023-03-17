package com.example.person.usecases;


import com.example.person.client.AddressClient;
import com.example.person.controller.PersonMapper;
import com.example.person.persistence.dto.PersonRespBody;
import com.example.person.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class FindByPeopleId {

    private final PersonGateway personService;
    private final AddressClient addressClient;


    public PersonRespBody execute(final Long id) {
        final var addresses = addressClient.listAllAddresses(id);
        final var person = personService.findById(id);
        return PersonMapper.personToDtoWithAddresses(person, addresses);
    }
}
