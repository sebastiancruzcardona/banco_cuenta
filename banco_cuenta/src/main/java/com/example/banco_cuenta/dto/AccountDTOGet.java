package com.example.banco_cuenta.dto;

import com.example.banco_cuenta.model.Account;

public class AccountDTOGet {

    private Long id;

    private String userName;

    private String userLastname;

    private String accountNumber;

    private String password;

    private double balance;

    private Long bankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public void convertToAccountDTO(Account account){
        this.setId(account.getId());
        this.setUserName(account.getUserName());
        this.setUserLastname(account.getUserLastname());
        this.setAccountNumber(account.getAccountNumber());
        this.setPassword(account.getPassword());
        this.setBalance(account.getBalance());
        this.setBankId(account.getBank().getId());
    }
}
