package com.bankmgmt.demo.DTO;

import lombok.*;

@Data
public class DepositOrWithdrawRequest {

    private String accountNumber;
    private Double amount;

}
