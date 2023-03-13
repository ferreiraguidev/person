package com.example.person.util;

import com.example.person.persistence.Person;

public class PersonCreator {

    public static Person createPersonToBeSaved(){
        return Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .build();
    }

    public static Person createValidPerson(){
        return Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("krokv")
                .id(1L)
                .build();
    }

    public static Person createValidUpdatedPerson(){
        return Person.builder()
                .cpf("1166598562")
                .rg("5789922")
                .email("fgfff@gmail.com")
                .name("same id than createValidPerson but diff name, for example")
                .id(1L)
                .build();
    }
}
