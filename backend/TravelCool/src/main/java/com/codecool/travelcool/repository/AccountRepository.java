package com.codecool.travelcool.repository;

import com.codecool.travelcool.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsAccountByEmail(String email);

    boolean existsAccountByEmailAndPassword( String email, String password);

    Account findAccountById(long id);
}

