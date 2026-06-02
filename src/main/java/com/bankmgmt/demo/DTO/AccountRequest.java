package com.bankmgmt.demo.DTO;

import lombok.Data;

@Data
public class AccountRequest {

    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer customerId;

}
