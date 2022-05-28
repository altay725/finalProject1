package com.example.demo.model;



import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "comp_accounts_tables")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "invalid full name")
    private String full_name;
    @NotBlank(message = "must be formatted 050-555-55-55")
    private String phone_number;
    @NotBlank(message = "invalid gender")
    private String gender;
    @NotBlank(message = "invalid email")
    private String email;
    @NotBlank(message = "invalid username")
    @Size(min = 4,max = 25,message = "short username")
    private String username;
    @NotBlank(message = "invalid password")
    @Size(min = 3,max = 30, message = "short password")
    private String password;
    @NotBlank(message = "invalid country, please choose country")
    private String country;


    @OneToMany(mappedBy = "theAccount")
    private List<Computer> computerList;
}