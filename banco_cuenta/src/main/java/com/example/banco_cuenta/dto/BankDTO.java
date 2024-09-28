package com.example.banco_cuenta.dto;

import jakarta.validation.constraints.*;

public class BankDTO {

    @NotBlank(message = "A name must be provided")
    @Size(min = 3, max = 60, message = "Not a valid name")
    private String name;

    @NotBlank(message = "An address must be provided")
    @Pattern(regexp = ".*\\b(calle|carrera)\\b.*", message = "Not a valid address")
    private String address;

    private String webSite;

    @NotBlank(message = "A customer support number must be provided")
    @Size(min = 10, max = 10, message = "Not a valid number. Ten digits numbers must be provided")
    @Pattern(regexp = "\\d+", message = "Only numbers must be provided")
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
