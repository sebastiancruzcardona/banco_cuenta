package com.example.banco_cuenta.controller;

import com.example.banco_cuenta.dto.BankDTO;
import com.example.banco_cuenta.dto.BankDTOGetPutPost;
import com.example.banco_cuenta.model.Bank;
import com.example.banco_cuenta.service.BankService;
import jakarta.validation.Valid;
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
    //Then, tries to map the Optional bankDTOGEtPutPost by using the .ok() function from ResponseEntity, for this the bank has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    //It is equivalent to writing:
        /*if(bankDTOGetPutPost.isPresent()){
          return ResponseEntity.ok(bankDTOGetPutPost);
        else{
          return ResponseEntity.notFound().build();
        }*/
    public ResponseEntity<BankDTOGetPutPost> getBankById(@PathVariable long id){
        Optional<BankDTOGetPutPost> bankDTOGetPutPost = bankService.findById(id);
        return bankDTOGetPutPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public BankDTOGetPutPost createBank(@Valid @RequestBody BankDTO bankDTO){
        return bankService.save(bankDTO);
    }

    @PutMapping("/{id}")
    //This method calls the update method from bankService that needs an id and a BankDTO object and returns an Optional
    //Then, tries to map the Optional bankDTOGetPutPost by using the .ok() function from ResponseEntity, for this the bankDTOGetPutPost has to be present
    //If the optional is empty, executes the orElseGet() implementing a ResponseEntity.notFound().build()
    public ResponseEntity<BankDTOGetPutPost> updateBank(@PathVariable long id, @Valid @RequestBody BankDTO bankDTO){
        Optional<BankDTOGetPutPost> bankDTOGetPutPost = bankService.update(id, bankDTO);
        return bankDTOGetPutPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
