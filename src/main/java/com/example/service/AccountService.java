package com.example.service;

import com.example.dto.AccountDTO;
import com.example.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long id);

    AccountDTO deposit(Long id,double amount);

    AccountDTO withdraw(Long id,double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long id);


}
