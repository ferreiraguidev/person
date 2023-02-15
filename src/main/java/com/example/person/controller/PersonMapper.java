package com.example.person.controller;


import com.example.person.persistence.dto.AddressRespBody;
import com.example.person.persistence.dto.PersonPostReqBody;
import com.example.person.persistence.dto.PersonRespBody;
import com.example.person.persistence.Person;

import java.util.List;


public class PersonMapper {

    public static PersonRespBody personToDto(Person person) {
        return PersonRespBody.builder()
                .id(person.getId())
                .cpf(person.getCpf())
                .name(person.getName())
                .rg(person.getRg())
                .email(person.getEmail())
                .build();
    }

    public static Person personToModel(PersonPostReqBody personPostReqBody) {
        return Person.builder()
                .cpf(personPostReqBody.getCpf())
                .name(personPostReqBody.getName())
                .rg(personPostReqBody.getRg())
                .email(personPostReqBody.getEmail())
                .build();
    }

    public static PersonRespBody personToDtoWithAddresses(Person person, List<AddressRespBody> addressRespBody) {
        return PersonRespBody.builder()
                .id(person.getId())
                .cpf(person.getCpf())
                .name(person.getName())
                .rg(person.getRg())
                .email(person.getEmail())
                .addresses(addressRespBody)
                // that was missing a list [];
                .build();
    }
}
