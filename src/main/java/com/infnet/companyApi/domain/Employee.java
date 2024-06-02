package com.infnet.companyApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String address;
    private Date addmissionDate;
    private Date demissionDate;
    private String gender;
    private String occupation;
    private float salary;
    private Date birthDate;


    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;




}
