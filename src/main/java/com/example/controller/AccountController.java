package com.example.controller;

import com.example.dto.AccountDTO;
import com.example.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


//    add account rest api
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

//    get account by id

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        AccountDTO accountById = accountService.getAccountById(id);
        return ResponseEntity.ok(accountById);
    }


    // deposit rest api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,@RequestBody Map<String , Double> request){
        Double amount = request.get("amount");

        AccountDTO accountDTO = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }

    //withdraw rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,@RequestBody Map<String ,Double> request){
        double amount = request.get("amount");
        AccountDTO accountDTO = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDTO);
    }


//    get all
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        List<AccountDTO> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }


//    delete account restapi
    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully...");
}

}
