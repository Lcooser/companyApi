package com.infnet.companyApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String cnpj;
    private String cpf;
    private String email;
    private String phone;
    private String address;


    @ManyToMany(mappedBy = "Client")
    private List<Company> companies;;


}
