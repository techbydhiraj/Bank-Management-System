package com.bankmgmt.demo.DTO;

import lombok.*;

@Data
public class TransOrReceiveMoney {

    private String fromAccountNumber;
    private String toAccountNumber;
    private Double amount;

}
