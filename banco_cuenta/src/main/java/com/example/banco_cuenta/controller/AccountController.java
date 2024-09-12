package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.model.Account;
import com.example.banco_cuenta.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id){
        Optional<Account> account = accountService.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Account createBank(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateBank(@PathVariable long id, @RequestBody AccountDTO accountDTODetails){
        Optional<Account> account = accountService.findById(id);
        if(account.isPresent()){
            Account updtatedAccount = account.get();
            updtatedAccount.setUserName(accountDTODetails.getUserName());
            updtatedAccount.setUserLastname(accountDTODetails.getUserLastname());
            updtatedAccount.setPassword(accountDTODetails.getPassword());
            updtatedAccount.setBalance(accountDTODetails.getBalance());
            return ResponseEntity.ok(accountService.save(updtatedAccount));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteBank(@PathVariable long id){
        if(accountService.findById(id).isPresent()){
            accountService.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
