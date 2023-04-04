package com.Simple.Api.Service;

import com.Simple.Api.Entity.AccountEntity;
import com.Simple.Api.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service


public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //Create account
    public AccountEntity createAccount(AccountEntity accountEntity) {
        // Set default role to USER
        // accountEntity.setRole(Role.USER);
        return accountRepository.save(accountEntity);
    }

    //Get all account
    public List<AccountEntity> getAllUser() {
        return accountRepository.findAll();
    }

      //Find one account
    public AccountEntity getUserById(Long id) {
        Optional<AccountEntity> userOptional = accountRepository.findById(id);
        return userOptional.orElse(null);
    }

    //Update account
    public void updateUser(AccountEntity accountEntity) {
        Optional<AccountEntity> existingAccount = accountRepository.findById(accountEntity.getId());
        if (existingAccount.isPresent()) {
            AccountEntity accountEntity1 = existingAccount.get();
            accountEntity1.setUsername(accountEntity.getUsername());
            accountEntity1.setPassword(accountEntity.getPassword());
            accountRepository.save(accountEntity1);
        }
    }

    //Delete account


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

