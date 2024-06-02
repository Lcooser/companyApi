package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter

public class SupplierDto {

    private UUID id;

    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;
    private Date startOfContract;
    private Date endOfContract;
    private String category;


}
