package com.bankmgmt.demo.Service;

import com.bankmgmt.demo.DTO.AccountRequest;
import com.bankmgmt.demo.DTO.DepositOrWithdrawRequest;
import com.bankmgmt.demo.DTO.TransOrReceiveMoney;
import com.bankmgmt.demo.Entity.*;
import com.bankmgmt.demo.Repository.AccountRepository;
import com.bankmgmt.demo.Repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        account.setAccountStatus(AccountStatusType.ACTIVE);

        account.setCustomer(customer);

        return accountRepository.save(account);

    }

    public Optional<Account> fetchAccountDetail(Integer id){
        return accountRepository.findById(id);
    }

    public List<Account> fetchAccountDetailByCusId(Integer id){

        return accountRepository.findByCustomerCusId(id);
    }

    private String generateUtr(){
        return "TXN" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + new Random().nextInt(10000);
    }

    private void createTransaction(Account account,Double amount,TransactionType type){

        Transaction transaction = new Transaction();
        String utr = generateUtr();
        transaction.setUtr(utr);
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setAccount(account);
        transactionRepository.save(transaction);

    }
    @Transactional
    public Account depositMoney(DepositOrWithdrawRequest request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (request.getAmount() <= 0) {
            throw new RuntimeException("Deposit amount must be greater than 0");
        }

        if (account.getAccountStatus() == AccountStatusType.ACTIVE){

            account.setBalance(account.getBalance() + request.getAmount());
            accountRepository.save(account);

            createTransaction(account,request.getAmount(),TransactionType.DEPOSIT);
        }
        else{
            throw new RuntimeException("Account is Not Active or blocked");
        }
           return account;
    }
@Transactional
    public Account withdrawMoney(DepositOrWithdrawRequest request){

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber());

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (request.getAmount() <= 0) {
            throw new RuntimeException("withdraw amount must be greater than 0");
        }

        if(account.getBalance() < request.getAmount()){
        throw new RuntimeException("Balance is insufficient");
        }
        if (account.getAccountStatus() == AccountStatusType.ACTIVE) {
                account.setBalance(account.getBalance() - request.getAmount());
                accountRepository.save(account);
                createTransaction(account,request.getAmount(),TransactionType.WITHDRAW);
        }
        else{
            throw new RuntimeException("Account is Not Active or blocked");
        }
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

    if (sender.getAccountStatus() == AccountStatusType.ACTIVE && receiver.getAccountStatus() == (AccountStatusType.ACTIVE)) {

        if (sender.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }else{
            receiver.setBalance(request.getAmount() + receiver.getBalance());
            sender.setBalance(sender.getBalance() - request.getAmount());
        }
        accountRepository.save(sender);
        accountRepository.save(receiver);

        createTransaction(receiver,request.getAmount(),TransactionType.DEPOSIT);
        createTransaction(sender,request.getAmount(),TransactionType.WITHDRAW);

    }
        if (sender.getAccountStatus() != AccountStatusType.ACTIVE) {
            throw new RuntimeException("Sender account is not active");
        }

        if (receiver.getAccountStatus() != AccountStatusType.ACTIVE) {
            throw new RuntimeException("Receiver account is not active");
        }

    return "Amount successfully transferred";
    }

    public String checkBalanceByAccountId(Integer id){

        Account account = accountRepository.findById(id).orElseThrow(  () -> new RuntimeException("Account Not Found") );

        return "Your balance is " + account.getBalance();

    }

    public String accountUnblockByAccountId(Integer id){
        Account account = accountRepository.findById(id).orElseThrow();
        account.setAccountStatus(AccountStatusType.ACTIVE);

        accountRepository.save(account);

        return "Account is unblocked";
    }

    public String accountBlockByAccountId(Integer id){
        Account account = accountRepository.findById(id).orElseThrow();
        account.setAccountStatus(AccountStatusType.BLOCKED);

        accountRepository.save(account);

        return "Account is blocked";
    }

    public String currentAccountStatus(Integer id){
        Account account = accountRepository.findById(id).orElseThrow();
        return "Current account status : " + account.getAccountStatus();
    }

    public String deleteAccount(Integer id){
        Account account = accountRepository.findById(id).orElseThrow();
        if(account.getBalance() == 0) {
            account.setAccountStatus(AccountStatusType.CLOSED);
            accountRepository.save(account);
        }else {
            throw new RuntimeException("Account can't be closed");
        }
        return "Account is Closed ";
    }
}

