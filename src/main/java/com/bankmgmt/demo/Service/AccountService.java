package com.bankmgmt.demo.Service;

import com.bankmgmt.demo.DTO.AccountRequest;
import com.bankmgmt.demo.DTO.DepositOrWithdrawRequest;
import com.bankmgmt.demo.DTO.TransOrReceiveMoney;
import com.bankmgmt.demo.Entity.Account;
import com.bankmgmt.demo.Entity.Customer;
import com.bankmgmt.demo.Entity.Transaction;
import com.bankmgmt.demo.Entity.TransactionType;
import com.bankmgmt.demo.Repository.AccountRepository;
import com.bankmgmt.demo.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    private TransactionRepository transactionRepository;

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

    public Account depositMoney(DepositOrWithdrawRequest request){

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());


        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Deposit amount must be greater than 0");
        }

        account.setBalance( account.getBalance() + request.getAmount());

        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transactionRepository.save(transaction);

           return account;
    }

    public Account withdrawMoney(DepositOrWithdrawRequest request){

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());


        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Deposit amount must be greater than 0");
        }

        if(account.getBalance() <= 0){
            throw new RuntimeException("Balance is insufficient");
        }

        account.setBalance( account.getBalance() - request.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return account;
    }

@Transactional
    public String transferMoney(TransOrReceiveMoney request){

         Account sender = accountRepository.findByAccountNumber(request.getFromAccountNumber());

         Account receiver = accountRepository.findByAccountNumber(request.getToAccountNumber());

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        if (sender == null) {
            throw new RuntimeException("Sender account not found");
        }

        if (receiver == null) {
        throw new RuntimeException("Receiver account not found");
        }

    if (sender.getAccountNumber().equals(receiver.getAccountNumber())) {
        throw new RuntimeException("Cannot transfer to same account");
    }

    if (sender.getBalance() < request.getAmount()) {
        throw new RuntimeException("Insufficient balance");
    }

    receiver.setBalance( request.getAmount() + receiver.getBalance() );
    sender.setBalance( sender.getBalance() - request.getAmount() );

    accountRepository.save(sender);
    accountRepository.save(receiver);


    Transaction creditTransaction = new Transaction();
    creditTransaction.setAmount(request.getAmount());
    creditTransaction.setTransactionType(TransactionType.DEPOSIT);
    creditTransaction.setAccount(receiver);
    transactionRepository.save(creditTransaction);

    Transaction receiveTransaction = new Transaction();
    receiveTransaction.setAmount(request.getAmount());
    receiveTransaction.setTransactionType(TransactionType.WITHDRAW);
    receiveTransaction.setAccount(sender);
    transactionRepository.save(receiveTransaction);

    return "Amount successfully transferred";
    }

    public String checkBalanceByAccountId(Integer id){

        Account account = accountRepository.findById(id).orElseThrow( () -> new RuntimeException("Account Not Found") );

        return "Your balance is " + account.getBalance();

    }
}

