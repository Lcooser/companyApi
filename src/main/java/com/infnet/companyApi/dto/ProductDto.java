package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter


public class ProductDto {

    private UUID id;

    private String name;
    private float price;
    private String description;
    private String category;
    private Date created;
    private Date updated;

}
