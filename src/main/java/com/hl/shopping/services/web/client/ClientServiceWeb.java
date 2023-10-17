package com.hl.shopping.services.web.client;

import com.hl.shopping.dto.ClientDto;
import com.hl.shopping.entites.Client;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ClientServiceWeb {

    public List<ClientDto> listClients();

    public Page<ClientDto> pageClients(int page, int size);

    public ClientDto findClientById(UUID id);

    public ClientDto addClient(ClientDto client);

    public ClientDto updateClient(ClientDto client);

    public ClientDto deleteClient(UUID id);

}
