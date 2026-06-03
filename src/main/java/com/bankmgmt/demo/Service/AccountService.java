package com.bankmgmt.demo.Service;

import com.bankmgmt.demo.DTO.AccountRequest;
import com.bankmgmt.demo.DTO.DepositRequest;
import com.bankmgmt.demo.Entity.Account;
import com.bankmgmt.demo.Entity.Customer;
import com.bankmgmt.demo.Repository.AccountRepository;
import com.bankmgmt.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    public Account createAccount(AccountRequest request){

        Customer customer = customerService.getCustomerById(request.getCustomerId()).orElseThrow( () -> new RuntimeException("Customer Not Found !"));

        Account account = new Account();

        account.setAccountNumber(request.getAccountNumber());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getBalance());

        account.setCustomer(customer);

        return accountRepository.save(account);

    }

    public Optional<Account> fetchAccountDetail(Integer id){
        return accountRepository.findById(id);
    }

    public List<Account> fetchAccountDetailByCusId(Integer id){

        return accountRepository.findByCustomerCusId(id);
    }

    public Account depositMoney(DepositRequest request){

           Account account = accountRepository.findByAccountNumber(request.getAccountNumber());


        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Deposit amount must be greater than 0");
        }

        Double updateBalance = account.getBalance() + request.getAmount();
        account.setBalance(updateBalance);

           return accountRepository.save(account);
    }
}

