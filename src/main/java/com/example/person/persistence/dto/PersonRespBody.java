package com.example.person.persistence.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PersonRespBody {
    private Long id;
    private String cpf;
    private String name;
    private String rg;
    private String email;
    private List<AddressRespBody> addresses;

}
