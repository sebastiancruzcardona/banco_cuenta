package com.example.banco_cuenta.service;

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

    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    public void deleteById(long id) {
        bankRepository.deleteById(id);
    }
}
