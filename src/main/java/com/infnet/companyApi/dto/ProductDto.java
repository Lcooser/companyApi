package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private float price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Created date is required")
    private Date created;

    @NotNull(message = "Updated date is required")
    private Date updated;

    private UUID companyId;
    private UUID productId;


}
