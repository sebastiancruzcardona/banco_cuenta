package com.example.banco_cuenta.service;

import com.example.banco_cuenta.model.Account;
import com.example.banco_cuenta.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Optional<Account> findById(long id){
        return accountRepository.findById(id);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public void deleteById(long id){
        accountRepository.deleteById(id);
    }
}
