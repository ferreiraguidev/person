package com.example.person.controller;


import com.example.person.persistence.dto.PersonPostReqBody;
import com.example.person.persistence.dto.PersonRespBody;
import com.example.person.gateway.PersonGateway;
import com.example.person.persistence.Person;
import com.example.person.usecases.FindByPeopleId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonGateway personGateway;
    private final FindByPeopleId findAddressesById;

    @ResponseStatus(OK)
    @GetMapping("person/all")
    public List<PersonRespBody> listAll() {
        return personGateway.listAll().stream().map(PersonMapper::personToDto).toList();
    }

    @ResponseStatus(OK)
    @GetMapping("person/{id}")
    public PersonRespBody findById(@PathVariable Long id) {
        return findAddressesById.execute(id);
    }

    @ResponseStatus(CREATED)
    @PostMapping("person/save")
    public Person save(@RequestBody PersonPostReqBody personPostReqBody) {
        return personGateway.save(PersonMapper.personToModel(personPostReqBody));
    }

    @ResponseStatus(OK)
    @GetMapping("person/find/{id}")
    public PersonRespBody findPersonById(@PathVariable Long id) {
        return PersonMapper.personToDto(personGateway.findById(id));
    }
}
