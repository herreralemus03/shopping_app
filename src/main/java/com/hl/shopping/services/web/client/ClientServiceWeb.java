package com.hl.shopping.services.web.client;

import com.hl.shopping.dto.ClientDto;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ClientServiceWeb {

    public List<Product> listClients();

    public Page<Product> pageClients(int page, int size);

    public Product findClientById(UUID id);

    public Product findClientByName(String name);

    public Product addClient(ClientDto client);

    public Product updateClient(ClientDto client);

    public Product deleteClient(ClientDto client);

}
