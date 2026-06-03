package com.bankmgmt.demo.Controller;


import com.bankmgmt.demo.DTO.AccountRequest;
import com.bankmgmt.demo.DTO.DepositRequest;
import com.bankmgmt.demo.Entity.Account;
import com.bankmgmt.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody AccountRequest request){

        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public Optional<Account> fetchAccountDetail(@PathVariable Integer id){

        return accountService.fetchAccountDetail(id);
    }

    @GetMapping("/customer/{id}")
    public List<Account> fetchAccountDetailByCusID(@PathVariable Integer id){

        return accountService.fetchAccountDetailByCusId(id);
    }

    @PutMapping("/deposit")
    public Account depositMoney(@RequestBody DepositRequest request){

        return accountService.depositMoney(request);
    }
}
