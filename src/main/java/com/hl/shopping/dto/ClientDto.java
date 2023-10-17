package com.hl.shopping.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClientDto {
    UUID id;
    String clientName;
    String clientLastName;
    String clientAddress;
}
