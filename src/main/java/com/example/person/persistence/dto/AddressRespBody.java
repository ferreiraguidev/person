package com.example.person.persistence.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AddressRespBody {
    private String cep;
    private String street;
    private String city;
    private Long personId;

}
