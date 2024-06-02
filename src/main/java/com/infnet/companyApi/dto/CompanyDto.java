package com.infnet.companyApi.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CompanyDto {

    private UUID id;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;
    private boolean isActive;
    private String category;
    private Date creationDate;;

}
