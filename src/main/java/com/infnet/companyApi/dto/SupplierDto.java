package com.infnet.companyApi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;



@Getter
@Setter
public class SupplierDto {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "[0-9]{14}", message = "CNPJ must have 14 digits")
    private String cnpj;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(min = 10, max = 14, message = "Phone number must be between 10 and 14 digits")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Start of contract date is required")
    private Date startOfContract;

    @NotNull(message = "End of contract date is required")
    private Date endOfContract;

    @NotBlank(message = "Category is required")
    private String category;

    private UUID companyId;


}
