package com.bankmgmt.demo.Controller;

import com.bankmgmt.demo.DTO.TransactionHistory;
import com.bankmgmt.demo.Entity.Transaction;
import com.bankmgmt.demo.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionHistoryByTransactionId(@PathVariable Integer id){
        return transactionService.getTransactionByTransactionId(id);
    }

    @PostMapping("/history")
    public List<Transaction> getTransactionHistory(@RequestBody TransactionHistory request){
        return transactionService.getTransactionByAccountId(request.getAccountNumber());
    }
}
