package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter

public class ClientDto {

    private UUID id;

    private String name;
    private String cnpj;
    private String cpf;
    private String email;
    private String phone;
    private String address;

}
