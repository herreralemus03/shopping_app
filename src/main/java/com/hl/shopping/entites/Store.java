package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "stores", schema = "shopping")
public class Store {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    private UUID id;

    @Getter @Setter
    @Column(name = "store_name")
    private String storeName;

    @Getter @Setter
    @Column(name = "store_code")
    private String storeCode;

}
