package com.example.banco_cuenta.dto;

import com.example.banco_cuenta.model.Bank;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountDTO {

    @NotBlank(message = "The user name must be provided")
    @Size(min = 1, max = 40, message = "Not a valid name.")
    private String userName;

    @NotBlank(message = "The user lastname must be provided")
    @Size(min = 1, max = 40, message = "Not a valid lastname.")
    private String userLastname;

    @NotBlank(message = "The user accountNumber must be provided")
    @Size(min = 11, max = 11, message = "Not a valid accountNumber.")
    private String accountNumber;

    @NotBlank(message = "A password must be provided")
    @Size(min = 4, max = 4, message = "Not a valid password. A password has to be 4 numbers long")
    @Pattern(regexp = "\\d+", message = "Only numbers must be provided")
    private String password;

    private double balance;

    private Long bankId;

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
}
