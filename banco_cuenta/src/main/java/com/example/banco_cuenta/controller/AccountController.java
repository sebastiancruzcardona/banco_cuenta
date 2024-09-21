package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.dto.AccountDTOGetPostPut;
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
    public List<AccountDTOGetPostPut> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTOGetPostPut> getAccountById(@PathVariable long id){
        Optional<AccountDTOGetPostPut> accountDTOGet = accountService.findById(id);
        return accountDTOGet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountDTOGetPostPut> createBank(@RequestBody AccountDTO accountDTO){
        //return accountService.save(accountDTO);
        //Is this correct? It was made to catch somehow a status if the insertion cannot be done because of the id
        Optional<AccountDTOGetPostPut> accountDTOGet = accountService.save(accountDTO);
        return accountDTOGet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTOGetPostPut> updateBank(@PathVariable long id, @RequestBody AccountDTOUpdate accountDTOUpdate){
        Optional<AccountDTOGetPostPut> account = accountService.update(id, accountDTOUpdate);
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
