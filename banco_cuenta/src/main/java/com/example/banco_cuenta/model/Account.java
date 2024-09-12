package com.example.banco_cuenta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 40)
    private String userName;

    @Column(name = "user_lastname", nullable = false, length = 40)
    private String userLastname;

    @Column(name = "account_number", nullable = false, length = 11, unique = true)
    private String accountNumber;

    @Column(nullable = false, length = 4)
    private String password;

    @Column(columnDefinition = "DOUBLE DEFAULT 0.0")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public Account() {
        super();
    }

    public Account(Long id, String userName, String userLastname, String password, double balance, Bank bank) {
        this.id = id;
        this.userName = userName;
        this.userLastname = userLastname;
        this.password = password;
        this.balance = balance;
        this.bank = bank;
    }

    public Account(String userName, String userLastname, String password, double balance, Bank bank) {
        this.userName = userName;
        this.userLastname = userLastname;
        this.password = password;
        this.balance = balance;
        this.bank = bank;
    }

    public Long getId() {
        return id;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
