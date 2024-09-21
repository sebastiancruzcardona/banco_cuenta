package com.example.banco_cuenta.service;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.dto.AccountDTOGetPostPut;
import com.example.banco_cuenta.dto.AccountDTOUpdate;
import com.example.banco_cuenta.model.Account;
import com.example.banco_cuenta.model.Bank;
import com.example.banco_cuenta.repository.AccountRepository;
import com.example.banco_cuenta.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    //This method finds all banks stored in database and returns a list of AccountDTOGetPutPost
    //Calls accountRepository.findAll() and uses a for cycle to iterate over the accounts and to add to the Arraylist to return
    public List<AccountDTOGetPostPut> findAll(){
        List<AccountDTOGetPostPut> accountsToReturn = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        for(Account account : accounts){
            AccountDTOGetPostPut accountDTOGetPostPut = new AccountDTOGetPostPut();
            accountDTOGetPostPut.convertToAccountDTO(account);
            accountsToReturn.add(accountDTOGetPostPut);
        }
        return accountsToReturn;
    }

    //This method returns an Optional of BankDTOGetPut
    //Using id, if the searched account exist, returns the optional, if not, returns an empty Optional
    public Optional<AccountDTOGetPostPut> findById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            AccountDTOGetPostPut accountDTOGetPostPut = new AccountDTOGetPostPut();
            accountDTOGetPostPut.convertToAccountDTO(account.get());
            return Optional.of(accountDTOGetPostPut);
        }
        return Optional.empty();
    }

    //This method returns an Optional of AccountDTOGetPostPut
    //First validates if the associated bank exist
    //Creates an Account object, sets its attributes from accountDTO received as parameter and saves it by calling accountRepository.save()
    //Uses that Account as an assistant to save calling the repository save() function
    //If the associated bank does not exist returns an empty Optional
    public Optional<AccountDTOGetPostPut> save(AccountDTO accountDTO){
        Optional<Bank> bank = bankRepository.findById(accountDTO.getBankId());
        if(bank.isPresent()){
            Account account = new Account();
            account.setUserName(accountDTO.getUserName());
            account.setUserLastname(accountDTO.getUserLastname());
            account.setAccountNumber(accountDTO.getAccountNumber());
            account.setPassword(accountDTO.getPassword());
            account.setBalance(accountDTO.getBalance());
            account.setBank(bank.get());
            AccountDTOGetPostPut savedAccountDTOGetPostPut = new AccountDTOGetPostPut();
            savedAccountDTOGetPostPut.convertToAccountDTO(accountRepository.save(account));
            return Optional.of(savedAccountDTOGetPostPut);
        }else{
            return Optional.empty();
        }
    }

    //This method returns an Optional of AccountDTOGetPostPut that can be present or empty.
    //First, it tries to find the account by id, then, if the Optional account is present, sets the attributes and returns an Optional
    //If there is not an account identified by that id, returns an empty optional
    public Optional<AccountDTOGetPostPut> update(long id, AccountDTOUpdate accountDTOUpdate){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            Account accountUpdate = account.get();
            accountUpdate.setUserName(accountDTOUpdate.getUserName());
            accountUpdate.setUserLastname(accountDTOUpdate.getUserLastname());
            accountUpdate.setPassword(accountDTOUpdate.getPassword());
            accountUpdate.setBalance(accountDTOUpdate.getBalance());
            accountRepository.save(accountUpdate);
            AccountDTOGetPostPut accountDTOGetPostPut = new AccountDTOGetPostPut();
            accountDTOGetPostPut.convertToAccountDTO(accountUpdate);
            return Optional.of(accountDTOGetPostPut);
        }
        return Optional.empty();
    }

    //This method, validating the Optional in the if block, returns true if deletion was made or false if not
    public boolean deleteById(long id){
        if(accountRepository.findById(id).isPresent()){
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
