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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private float price;
    private String description;
    private String category;
    private Date created;
    private Date updated;

    @ManyToMany(mappedBy = "products")
    private List<Company> companies = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Supplier> suppliers = new ArrayList<>();
}
