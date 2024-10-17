package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.UUID;

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
// import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.ClientRepository;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.servlet.http.HttpServletResponse;

/**
 * TODO ajouter la/les annotations nécessaires pour faire de "ClientRestController" un contrôleur de REST API
 */
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
        clientRepository.save(client);
        return client;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client client) {
        Optional<Client> oldClient = clientRepository.findById(id);
        if (!oldClient.isPresent()){
            return (ResponseEntity<Client>) ResponseEntity.badRequest();

        }
        return ResponseEntity.ok(clientRepository.save(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable UUID id, HttpServletResponse response) {
        Optional<Client> oldClient = clientRepository.findById(id);
        if (!oldClient.isPresent()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        clientRepository.delete(oldClient.get());
        return ResponseEntity.ok(true);
    }
}
