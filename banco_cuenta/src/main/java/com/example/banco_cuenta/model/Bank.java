package com.example.banco_cuenta.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String address;

    @Column(name = "web_site", nullable = true)
    private String webSite;

    @Column(name = "costumer_support_number", nullable = false, length = 10, unique = true)
    private String costumerSupportNumber;

    @Column(nullable = false, length = 60, unique = true)
    private String email;

    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;

    public Bank() {
        super();
    }

    public Bank(Long id, String name, String address, String webSite, String costumerSupportNumber, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.webSite = webSite;
        this.costumerSupportNumber = costumerSupportNumber;
        this.email = email;
    }

    public Bank(String name, String address, String webSite, String costumerSupportNumber, String email) {
        this.name = name;
        this.address = address;
        this.webSite = webSite;
        this.costumerSupportNumber = costumerSupportNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getCostumerSupportNumber() {
        return costumerSupportNumber;
    }

    public void setCostumerSupportNumber(String costumerSupportNumber) {
        this.costumerSupportNumber = costumerSupportNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
