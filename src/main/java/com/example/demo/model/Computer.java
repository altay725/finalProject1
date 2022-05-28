package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@Table(name = "computer_table")
@Entity

public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "fill all the fields")
    private String marka;
    @NotBlank(message = "invalid model")
    private String comp_model;
    @NotBlank(message = "invalid price")
    private String price;
    @NotBlank(message = "invalid description ")
    private String description;
    @NotBlank(message = "choose the condition")
    @Column(name = "computer_condition")
    private String condition;

    private String imageLink;
    @NotBlank(message = "fill all the fields")
    private String op_memory;
    @NotBlank(message = "invalid processor")
    private String processor;
    @NotBlank(message = "invalid memory")
    private String pamyati;
    @NotBlank(message = "invalid type of memory")
    private String tip_pamyati;
    @NotBlank(message = "invalid operation system")
    private String operac_sistema;
    @NotBlank(message = "invalid video card")
    private String video_card;


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "account_id")
    private Account theAccount;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "order_computer",
            joinColumns = @JoinColumn(name ="computer_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Order> orderList;

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", comp_model='" + comp_model + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", condition='" + condition + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", op_memory='" + op_memory + '\'' +
                ", processor='" + processor + '\'' +
                ", pamyati='" + pamyati + '\'' +
                ", tip_pamyati='" + tip_pamyati + '\'' +
                ", operac_sistema='" + operac_sistema + '\'' +
                ", video_card='" + video_card + '\'' +
                ", theAccount=" + theAccount +
                '}';
    }
}