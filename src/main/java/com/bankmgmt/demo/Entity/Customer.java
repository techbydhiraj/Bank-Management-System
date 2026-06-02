package com.bankmgmt.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="customers")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusId;

    @Column( length = 50 , nullable = false)
    @NotBlank( message = "Customer name is required" )
    private String cusName;

    @Column( unique = true , length = 100 , nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @Column( nullable = false , length = 10 )
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @Column( length = 255 )
    private String address;


    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Account> accounts;
}
