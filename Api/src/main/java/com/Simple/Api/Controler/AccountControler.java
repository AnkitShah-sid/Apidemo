package com.Simple.Api.Controler;

import com.Simple.Api.Entity.AccountEntity;
import com.Simple.Api.Repository.AccountRepository;
import com.Simple.Api.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class AccountControler {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts/")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity account) {
        AccountEntity newAccount = accountService.createAccount(account);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAccount.getId())
                .toUri();
        return ResponseEntity.created(location).body(newAccount);
    }

    @GetMapping("/accounts/")
    public List<AccountEntity> getAll() {
        return accountService.getAllUser();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountEntity> getUserById(@PathVariable Long id) {
        AccountEntity user = accountService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable("id") Long id, @RequestBody  AccountEntity accountEntity) {
        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity.setId(id);
        accountEntity.setUsername(accountEntity.getUsername());
        accountEntity.setPassword(accountEntity.getPassword());
        accountService.updateUser(accountEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        accountService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}

// Other methods in the controller