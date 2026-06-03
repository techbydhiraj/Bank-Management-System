package com.bankmgmt.demo.DTO;

import lombok.*;

@Data
public class DepositRequest {

    private String accountNumber;
    private Double amount;

}
