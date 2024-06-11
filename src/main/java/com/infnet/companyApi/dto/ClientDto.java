package com.infnet.companyApi.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class ClientDto {

    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "CNPJ is mandatory")
    private String cnpj;

    @NotBlank(message = "CPF is mandatory")
    private String cpf;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Address is mandatory")
    private String address;
}
