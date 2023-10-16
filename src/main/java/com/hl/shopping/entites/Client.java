package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "clients", schema = "shopping")
public class Client {

    @Id
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @Getter @Setter
    @Column(name = "client_name")
    String clientName;

    @Getter @Setter
    @Column(name = "client_lastname")
    String clientLastName;

    @Getter @Setter
    @Column(name = "client_address")
    String clientAddress;

}
