package com.example.person.client;


import com.example.person.persistence.dto.AddressRespBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(url = "localhost:8080",name = "addressClient")
public interface AddressClient {

    @GetMapping("{id}")
    List<AddressRespBody> listAllAddresses(@PathVariable Long id);
}
