package com.infnet.companyApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;
    private Date startOfContract;
    private Date endOfContract;
    private String category;

    @ManyToMany(mappedBy = "suppliers")
    private List<Company> companies;

    @ManyToMany
    private List<Product> products = new ArrayList<>();
}
