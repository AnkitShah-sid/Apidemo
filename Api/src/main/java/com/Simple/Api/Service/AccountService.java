package com.Simple.Api.Service;
import com.Simple.Api.Entity.AccountEntity;
import com.Simple.Api.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class AccountService {

     @Autowired
        private AccountRepository accountRepository;

        public AccountEntity createAccount(AccountEntity accountEntity) {
            // Set default role to USER
           // accountEntity.setRole(Role.USER);
            return accountRepository.save(accountEntity);
        }
         public List<AccountEntity> getAllUser(){
           return accountRepository.findAll();
         }

    public AccountEntity getUserById(Long id) {
        Optional<AccountEntity> userOptional = accountRepository.findById(id);
        return userOptional.orElse(null);
      }

    public void updateUser(AccountEntity accountEntity) {
        Optional<AccountEntity> existingAccount = accountRepository.findById(accountEntity.getId());
        if(existingAccount.isPresent()){
            AccountEntity accountEntity1 = existingAccount.get();
            accountEntity1.setUsername(accountEntity.getUsername());
            accountEntity1.setPassword(accountEntity.getPassword());
            accountRepository.save(accountEntity1);
        }
     }

}

