package com.bankmgmt.demo.Service;

import com.bankmgmt.demo.Entity.Transaction;
import com.bankmgmt.demo.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> getTransactionByTransactionId(Integer id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactionByAccountId(String accountNumber){
        return transactionRepository.findByAccountAccountNumber(accountNumber);
    }
}
