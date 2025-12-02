package com.example.service;

import com.example.dto.AccountDTO;
import com.example.entity.Account;
import com.example.mapper.AccountMapper;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(saveAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exits.."));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exits.."));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account save = accountRepository.save(account);

        return AccountMapper.mapToAccountDTO(save);
    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exits.."));

        if (account.getBalance() < amount){
            throw  new RuntimeException("insufficient amount...");
        }

        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);



        return AccountMapper.mapToAccountDTO(saveAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {

        List<Account> all = accountRepository.findAll();
       return all.stream().map((account )->AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exits.."));
        accountRepository.deleteById(id);


    }
}
