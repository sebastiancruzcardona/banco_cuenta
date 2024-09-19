package com.example.banco_cuenta.service;

import com.example.banco_cuenta.dto.BankDTO;
import com.example.banco_cuenta.model.Bank;
import com.example.banco_cuenta.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Optional<Bank> findById(long id) {
        return bankRepository.findById(id);
    }

    public Bank save(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.getName());
        bank.setAddress(bankDTO.getAddress());
        bank.setWebSite(bankDTO.getWebSite());
        bank.setCostumerSupportNumber(bankDTO.getCostumerSupportNumber());
        bank.setEmail(bankDTO.getEmail());
        return bankRepository.save(bank);
    }

    public Optional<Bank> update(long id, BankDTO bankDTO){
        Optional<Bank> bank = findById(id);
        if(bank.isPresent()){
            Bank updatedBank = bank.get();
            updatedBank.setName(bankDTO.getName());
            updatedBank.setAddress(bankDTO.getAddress());
            updatedBank.setWebSite(bankDTO.getWebSite());
            updatedBank.setCostumerSupportNumber(bankDTO.getCostumerSupportNumber());
            updatedBank.setEmail(bankDTO.getEmail());
            return Optional.of(bankRepository.save(updatedBank));
        }else{
            return Optional.empty();
        }
    }

    public boolean deleteById(long id) {
        if(findById(id).isPresent()){
            bankRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
