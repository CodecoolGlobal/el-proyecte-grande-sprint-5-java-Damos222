package com.codecool.travelcool.model;

import com.codecool.travelcool.dto.AccountDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne
    @JsonIgnore
    private Address address;

    public AccountDto toAccountDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(email);
        accountDto.setPassword(password);
        accountDto.setPassword(firstName);
        accountDto.setPassword(lastName);
        return accountDto;
    }
}
