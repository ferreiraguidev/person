package com.example.person.persistence.dto;


import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class PersonPostReqBody {
    private String cpf;
    private String name;
    private String rg;

    @Email
    private String email;
}
