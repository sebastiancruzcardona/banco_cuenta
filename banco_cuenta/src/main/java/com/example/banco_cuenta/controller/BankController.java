package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.BankDTO;
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
    public List<Bank> getAllBanks(){
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable long id){
        Optional<Bank> bank = bankService.findById(id);
        return bank.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bank createBank(@RequestBody Bank bank){
        return bankService.save(bank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable long id, @RequestBody BankDTO bankDTODetails){
        Optional<Bank> bank = bankService.findById(id);
        if(bank.isPresent()){
            Bank updtatedBank = bank.get();
            updtatedBank.setName(bankDTODetails.getName());
            updtatedBank.setAddress(bankDTODetails.getAddress());
            updtatedBank.setWebSite(bankDTODetails.getWebSite());
            updtatedBank.setCostumerSupportNumber(bankDTODetails.getCostumerSupportNumber());
            updtatedBank.setEmail(bankDTODetails.getEmail());
            return ResponseEntity.ok(bankService.save(updtatedBank));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bank> deleteBank(@PathVariable long id){
        if(bankService.findById(id).isPresent()){
            bankService.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
