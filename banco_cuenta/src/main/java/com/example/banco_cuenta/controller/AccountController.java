package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.dto.AccountDTOUpdate;
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
    public Account createBank(@RequestBody AccountDTO accountDTO){
        return accountService.save(accountDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateBank(@PathVariable long id, @RequestBody AccountDTOUpdate accountDTOUpdate){
        Optional<Account> account = accountService.update(id, accountDTOUpdate);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable long id){
        if(accountService.deleteById(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
