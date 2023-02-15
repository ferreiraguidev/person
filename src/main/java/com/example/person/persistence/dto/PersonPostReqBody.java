package com.example.person.persistence.dto;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonPostReqBody {

    private String cpf;
    private String name;
    private String rg;

    @Email
    private String email;
}
