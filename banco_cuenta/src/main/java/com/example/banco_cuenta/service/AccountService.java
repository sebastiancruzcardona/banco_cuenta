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

    /*public Optional<Account> findById(long id){
        return accountRepository.findById(id);
    }*/

    public Optional<AccountDTOGetPostPut> findById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            AccountDTOGetPostPut accountDTOGetPostPut = new AccountDTOGetPostPut();
            accountDTOGetPostPut.convertToAccountDTO(account.get());
            return Optional.of(accountDTOGetPostPut);
        }
        return Optional.empty();
    }

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
        //bank.ifPresent(account::setBank); //Try catch block missing to handle if is empty
        //AccountDTOGet savedAccountDTOGet = new AccountDTOGet();
        //savedAccountDTOGet.convertToAccountDTO(accountRepository.save(account));
        //return savedAccountDTOGet;
    }

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

    public boolean deleteById(long id){
        if(accountRepository.findById(id).isPresent()){
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
