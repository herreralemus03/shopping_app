package com.hl.shopping.services.datasource.client;

import com.hl.shopping.entites.Client;
import com.hl.shopping.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    final ClientRepository clientRepository;

    public ClientServiceImpl(
            ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAllListed() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> findAllPaged(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client add(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client delete(Client client) {
        Client current = this.findById(client.getId());
        clientRepository.delete(current);
        return current;
    }
}
