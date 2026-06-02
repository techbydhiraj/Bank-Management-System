package com.bankmgmt.demo.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = " accounts ")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer accountId;

    @Column( unique = true , length = 12 , nullable = false)
    private String accountNumber;

    @Column( nullable = false)
    private String accountType;

    private Double balance;

    //relationship
    @ManyToOne
    @JoinColumn( name = "customerId")
    @JsonBackReference
    private  Customer customer;



}
