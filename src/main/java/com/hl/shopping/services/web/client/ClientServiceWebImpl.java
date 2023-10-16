package com.hl.shopping.services.web.client;

import com.hl.shopping.dto.ClientDto;
import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.client.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceWebImpl implements ClientServiceWeb {

    final ClientService clientService;

    public ClientServiceWebImpl (
            ClientService clientService
    ){
        this.clientService = clientService;
    }

    @Override
    public List<Product> listClients() {
        return null;
    }

    @Override
    public Page<Product> pageClients(int page, int size) {
        return null;
    }

    @Override
    public Product findClientById(UUID id) {
        return null;
    }

    @Override
    public Product findClientByName(String name) {
        return null;
    }

    @Override
    public Product addClient(ClientDto client) {
        return null;
    }

    @Override
    public Product updateClient(ClientDto client) {
        return null;
    }

    @Override
    public Product deleteClient(ClientDto client) {
        return null;
    }
}
