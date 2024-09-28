package com.example.banco_cuenta.dto;

import jakarta.validation.constraints.*;

public class BankDTO {

    @NotNull(message = "A name must be provided")
    @Size(min = 3, max = 60, message = "Not a valid name")
    private String name;

    @NotNull(message = "An address must be provided")
    private String address;

    private String webSite;

    private String costumerSupportNumber;

    @Email(message = "An email must be provided")
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
