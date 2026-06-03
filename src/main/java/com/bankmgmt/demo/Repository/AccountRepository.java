package com.bankmgmt.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bankmgmt.demo.Entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account , Integer> {

    List<Account> findByCustomerCusId(Integer cusId);

    Account findByAccountNumber(String accountNumber);
}
