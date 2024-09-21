package com.example.banco_cuenta.service;

import com.example.banco_cuenta.dto.BankDTO;
import com.example.banco_cuenta.dto.BankDTOGet;
import com.example.banco_cuenta.model.Bank;
import com.example.banco_cuenta.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public List<BankDTOGet> findAll() {
        List<BankDTOGet> banksToReturn = new ArrayList<>();
        List<Bank> banks = bankRepository.findAll();
        for (Bank bank : banks) {
            BankDTOGet bankDTOGet = new BankDTOGet();
            bankDTOGet.convertToBankDTO(bank);
            banksToReturn.add(bankDTOGet);
        }
        return banksToReturn;
    }

    public Optional<BankDTOGet> findById(long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if(bank.isPresent()) {
            BankDTOGet bankDTOGet = new BankDTOGet();
            bankDTOGet.convertToBankDTO(bank.get());
            return Optional.of(bankDTOGet);
        }
        return Optional.empty();
    }

    //This method creates a Bank object, sets its attributes from bankDTO and saves it by calling bankRepository.save()
    public Bank save(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.getName()); //Is it necessary to try catch for a null pointer ex?
        bank.setAddress(bankDTO.getAddress());
        bank.setWebSite(bankDTO.getWebSite());
        bank.setCostumerSupportNumber(bankDTO.getCostumerSupportNumber());
        bank.setEmail(bankDTO.getEmail());
        return bankRepository.save(bank);
    }

    //This method returns an Optional that can be present or empty.
    //First, it tries to find the bank by id, then, if the Optional bank is present, sets the attributes and returns an Optional
    //If there is not a bank that identified by that id, returns an empty optional
    public Optional<Bank> update(long id, BankDTO bankDTO){
        Optional<Bank> bank = bankRepository.findById(id);
        if(bank.isPresent()){
            Bank updatedBank = bank.get();
            updatedBank.setName(bankDTO.getName());//Is it necessary to try catch for a null pointer ex?
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
        if(bankRepository.findById(id).isPresent()) {
            bankRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
