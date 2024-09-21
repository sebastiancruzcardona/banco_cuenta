package com.example.banco_cuenta.service;

import com.example.banco_cuenta.dto.AccountDTO;
import com.example.banco_cuenta.dto.AccountDTOGet;
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

    public List<AccountDTOGet> findAll(){
        List<AccountDTOGet> accountsToReturn = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        for(Account account : accounts){
            AccountDTOGet accountDTOGet = new AccountDTOGet();
            accountDTOGet.convertToAccountDTO(account);
            accountsToReturn.add(accountDTOGet);
        }
        return accountsToReturn;
    }

    /*public Optional<Account> findById(long id){
        return accountRepository.findById(id);
    }*/

    public AccountDTOGet findById(Long id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            AccountDTOGet accountDTOGet = new AccountDTOGet();
            accountDTOGet.convertToAccountDTO(account.get());
            return accountDTOGet;
        }
        return null;
    }

    public Account save(AccountDTO accountDTO){
        Account account = new Account();
        account.setUserName(accountDTO.getUserName());
        account.setUserLastname(accountDTO.getUserLastname());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setPassword(accountDTO.getPassword());
        account.setBalance(accountDTO.getBalance());
        Optional<Bank> bank = bankRepository.findById(accountDTO.getBankId());
        bank.ifPresent(account::setBank); //Try catch block missing to handle if is empty
        return accountRepository.save(account);
    }

    public Optional<Account> update(long id, AccountDTOUpdate accountDTOUpdate){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            Account accountUpdate = account.get();
            accountUpdate.setUserName(accountDTOUpdate.getUserName());
            accountUpdate.setUserLastname(accountDTOUpdate.getUserLastname());
            accountUpdate.setPassword(accountDTOUpdate.getPassword());
            accountUpdate.setBalance(accountDTOUpdate.getBalance());
            return Optional.of(accountRepository.save(accountUpdate));
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
