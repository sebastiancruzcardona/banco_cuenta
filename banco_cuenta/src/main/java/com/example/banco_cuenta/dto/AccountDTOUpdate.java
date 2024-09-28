package com.example.banco_cuenta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountDTOUpdate {

    @NotBlank(message = "The user name must be provided")
    @Size(min = 1, max = 40, message = "Not a valid name.")
    private String userName;

    @NotBlank(message = "The user lastname must be provided")
    @Size(min = 1, max = 40, message = "Not a valid lastname.")
    private String userLastname;

    @NotBlank(message = "The user accountNumber must be provided")
    @Size(min = 11, max = 11, message = "Not a valid accountNumber.")
    private String password;

    private double balance;

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
}
