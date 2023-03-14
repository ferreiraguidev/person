package com.example.person.util;

import com.example.person.persistence.dto.PersonPostReqBody;


public class PersonPostReqBodyCreator {

    public static PersonPostReqBody createPersonPostReqBody(){
        return PersonPostReqBody.builder()
                .cpf(PersonCreator.createValidPersonReqBody().getCpf())
                .rg(PersonCreator.createValidPersonReqBody().getRg())
                .name(PersonCreator.createValidPersonReqBody().getName())
                .email(PersonCreator.createValidPersonReqBody().getEmail())
                .build();
    }
}
