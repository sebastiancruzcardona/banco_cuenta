package com.example.banco_cuenta.service;

import com.example.banco_cuenta.dto.BankDTO;
import com.example.banco_cuenta.dto.BankDTOGetPutPost;
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

    //This method finds all banks stored in database and returns a list o BankDTOGetPutPost
    //Calls bankRepository.findAll() and uses a for cycle to iterate over the banks and to add to de Arraylist to return
    public List<BankDTOGetPutPost> findAll() {
        List<BankDTOGetPutPost> banksToReturn = new ArrayList<>();
        List<Bank> banks = bankRepository.findAll();
        for (Bank bank : banks) {
            BankDTOGetPutPost bankDTOGetPutPost = new BankDTOGetPutPost();
            bankDTOGetPutPost.convertToBankDTO(bank);
            banksToReturn.add(bankDTOGetPutPost);
        }
        return banksToReturn;
    }

    //This method returns an Optional of BankDTOGetPut
    //Using id, if the searched bank exist, returns the optional, if not, returns an empty Optional
    public Optional<BankDTOGetPutPost> findById(long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if(bank.isPresent()) {
            BankDTOGetPutPost bankDTOGetPutPost = new BankDTOGetPutPost();
            bankDTOGetPutPost.convertToBankDTO(bank.get());
            return Optional.of(bankDTOGetPutPost);
        }
        return Optional.empty();
    }

    //This method return BankDTOGetPutPost object
    //Creates a Bank object, sets its attributes from bankDTO received as parameter and saves it by calling bankRepository.save()
    //Uses a Bank as an assistant to save calling the repository save() function
    public BankDTOGetPutPost save(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.getName());
        bank.setAddress(bankDTO.getAddress());
        bank.setWebSite(bankDTO.getWebSite());
        bank.setCostumerSupportNumber(bankDTO.getCostumerSupportNumber());
        bank.setEmail(bankDTO.getEmail());
        BankDTOGetPutPost bankDTOGetPutPost = new BankDTOGetPutPost();
        bankDTOGetPutPost.convertToBankDTO(bankRepository.save(bank));
        return bankDTOGetPutPost;
    }

    //This method returns an Optional that can be present or empty.
    //First, it tries to find the bank by id, then, if the Optional bank is present, sets the attributes and returns an Optional
    //If there is not a bank that identified by that id, returns an empty optional
    public Optional<BankDTOGetPutPost> update(long id, BankDTO bankDTO){
        Optional<Bank> bank = bankRepository.findById(id);
        if(bank.isPresent()){
            Bank updatedBank = bank.get();
            updatedBank.setName(bankDTO.getName());//Is it necessary to try catch for a null pointer ex?
            updatedBank.setAddress(bankDTO.getAddress());
            updatedBank.setWebSite(bankDTO.getWebSite());
            updatedBank.setCostumerSupportNumber(bankDTO.getCostumerSupportNumber());
            updatedBank.setEmail(bankDTO.getEmail());
            BankDTOGetPutPost bankDTOGetPutPost = new BankDTOGetPutPost();
            bankDTOGetPutPost.convertToBankDTO(bankRepository.save(updatedBank));
            return Optional.of(bankDTOGetPutPost);
        }else{
            return Optional.empty();
        }
    }

    //This method, validating the Optional in the if block, returns true if deletion was made or false if not
    public boolean deleteById(long id) {
        if(bankRepository.findById(id).isPresent()) {
            bankRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
