package com.bankmgmt.demo.Repository;

import com.bankmgmt.demo.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByAccountAccountNumber(String accountNumber);
}
