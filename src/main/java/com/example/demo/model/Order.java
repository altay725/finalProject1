package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "order_table")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "invalid name")
    private String customerFullName;
    @NotBlank(message = "invalid address")
    private String customerAddress;
    @NotBlank(message = "invalid amount")
    private String payAmount;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "order_computer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "computer_id")

    )


    private List<Computer> orderedComputerList = new ArrayList<>();
}




