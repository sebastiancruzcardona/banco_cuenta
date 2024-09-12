package com.example.banco_cuenta.dto;

import com.example.banco_cuenta.model.Account;
import jakarta.persistence.*;

import java.util.List;

public class BankDTO {

    private String name;

    private String address;

    private String webSite;

    private String costumerSupportNumber;

    private String email;

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
}
