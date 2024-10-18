package fr.afpa.orm.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/clients")
public class ClientRestController {
 
    private ClientRepository clientRepository;

    public ClientRestController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        
    }



    @GetMapping
    public List<Client> getAll() {
        return (List<Client>) clientRepository.findAll(); 
    }


    @GetMapping("/{id}")
    public Optional<Client> getOne(@PathVariable UUID id) {
        return clientRepository.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        // client.setAccounts(new ArrayList<>());
        clientRepository.save(client);
        return client;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        Optional<Client> oldClient = clientRepository.findById(id);
        if (!oldClient.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> remove(@PathVariable UUID id) {
        Optional<Client> oldClient = clientRepository.findById(id);
        if (!oldClient.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        clientRepository.delete(oldClient.get());
        return ResponseEntity.ok(oldClient.get());
    }
}
