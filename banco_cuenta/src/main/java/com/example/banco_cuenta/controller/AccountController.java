package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.dto.AccountDTOGetPostPut;
import com.example.banco_cuenta.dto.AccountDTOUpdate;
import com.example.banco_cuenta.model.Account;
import com.example.banco_cuenta.service.AccountService;
import jakarta.validation.Valid;
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
    //This method calls the findById method from accountService that returns an Optional
    //Then, tries to map the Optional AccountDTOGetPostPut by using the .ok() function from ResponseEntity, for this the account has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    //It is equivalent to writing:
        /*if(accountDTOGetPutPost.isPresent()){
          return ResponseEntity.ok(accountDTOGetPutPost);
        else{
          return ResponseEntity.notFound().build();
        }*/
    public ResponseEntity<AccountDTOGetPostPut> getAccountById(@PathVariable long id){
        Optional<AccountDTOGetPostPut> accountDTOGetPutPost = accountService.findById(id);
        return accountDTOGetPutPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    //This method calls the save method from accountService that needs an accountDTO object and returns an Optional
    //Then, tries to map the Optional accountDTOGetPostPut by using the .ok() function from ResponseEntity, for this the accountDTOGetPostPut has to be present
    public ResponseEntity<AccountDTOGetPostPut> createBank(@Valid @RequestBody AccountDTO accountDTO){
        Optional<AccountDTOGetPostPut> accountDTOGetPostPut = accountService.save(accountDTO);
        return accountDTOGetPostPut.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    //This method calls the update method from accountService that needs an id and a AccountDTOUpdate object and returns an Optional
    //Then, tries to map the Optional accountDTOGetPostPut by using the .ok() function from ResponseEntity, for this the accountDTOGetPostPut has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    public ResponseEntity<AccountDTOGetPostPut> updateBank(@PathVariable long id, @Valid @RequestBody AccountDTOUpdate accountDTOUpdate){
        Optional<AccountDTOGetPostPut> accountDTOGetPostPut = accountService.update(id, accountDTOUpdate);
        return accountDTOGetPostPut.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
