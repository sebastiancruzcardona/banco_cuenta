package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.BankDTO;
import com.example.banco_cuenta.dto.BankDTOGetPutPost;
import com.example.banco_cuenta.model.Bank;
import com.example.banco_cuenta.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping
    public List<BankDTOGetPutPost> getAllBanks(){
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    //This method calls the findById method from bankService that returns an Optional
    //Then, tries to map the Optional bank by using the .ok() function from ResponseEntity, for this the bank has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    //It is equivalent to writing:
        /*if(bank.isPresent()){
          return ResponseEntity.ok(bank);
        else{
          return ResponseEntity.notFound().build();
        }*/
    public ResponseEntity<BankDTOGetPutPost> getBankById(@PathVariable long id){
        Optional<BankDTOGetPutPost> bankDTOGet = bankService.findById(id);
        return bankDTOGet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BankDTOGetPutPost createBank(@RequestBody BankDTO bankDTO){
        return bankService.save(bankDTO);
    }

    @PutMapping("/{id}")
    //This method calls the update method from bankService that needs an id and a BankDTO object and returns an Optional
    //Then, tries to map the Optional bank by using the .ok() function from ResponseEntity, for this the bank has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    //It is equivalent to writing:
        /*if(bank.isPresent()){
          return ResponseEntity.ok(bank);
        else{
          return ResponseEntity.notFound().build();
        }*/
    public ResponseEntity<BankDTOGetPutPost> updateBank(@PathVariable long id, @RequestBody BankDTO bankDTO){
        Optional<BankDTOGetPutPost> bank = bankService.update(id, bankDTO);
        return bank.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteBank(@PathVariable long id){
        if(bankService.deleteById(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
