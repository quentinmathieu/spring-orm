package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.FixtureGenerator;
import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {
 
    private AccountRepository accountRepository;

    public AccountRestController(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        // new FixtureGenerator(accountRepository, clientRepository);
    }



    @GetMapping
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll(); 
    }

   
    @GetMapping("/{id}")
    public Optional<Account> getOne(@PathVariable long id) {
        return accountRepository.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        accountRepository.save(account);
        return account;
    }

    @SuppressWarnings("unchecked")
    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable long id, @RequestBody Account account) {
        Optional<Account> oldAccount = accountRepository.findById(id);
        if (!oldAccount.isPresent()){
            System.out.println("meh");
            return (ResponseEntity<Account>) ResponseEntity.badRequest();
        }
        return ResponseEntity.ok(accountRepository.save(account));
    }

    public boolean remove(@PathVariable long id, HttpServletResponse response) {
        Optional<Account> oldAccount = accountRepository.findById(id);
        if (!oldAccount.isPresent()){
            return false;
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return true;
    }
}
