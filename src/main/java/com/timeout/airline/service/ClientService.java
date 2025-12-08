package com.timeout.airline.service;

import com.timeout.airline.entity.Client;
import com.timeout.airline.exception.ResourceNotFoundException;
import com.timeout.airline.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    // Create client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    
    // Get all clients
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    
    // Get client by ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }
    
    // Get client by passport
    public Client getClientByPassport(String passport) {
        return clientRepository.findByNumPassport(passport)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with passport: " + passport));
    }
    
    // Update client
    public Client updateClient(Long id, Client clientDetails) {
        Client client = getClientById(id);
        
        client.setFirstname(clientDetails.getFirstname());
        client.setLastname(clientDetails.getLastname());
        client.setAddress(clientDetails.getAddress());
        client.setEmail(clientDetails.getEmail());
        client.setPhone(clientDetails.getPhone());
        client.setBirthdate(clientDetails.getBirthdate());
        client.setNumPassport(clientDetails.getNumPassport());
        
        return clientRepository.save(client);
    }
    
    // Delete client
    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }
}