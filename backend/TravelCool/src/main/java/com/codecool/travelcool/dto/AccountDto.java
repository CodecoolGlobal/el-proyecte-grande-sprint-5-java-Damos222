package com.codecool.travelcool.dto;

import com.codecool.travelcool.model.Account;
import com.codecool.travelcool.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private AddressDto address;

    public Account getAccount() {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setPassword(firstName);
        account.setPassword(lastName);
        return account;
    }
}
