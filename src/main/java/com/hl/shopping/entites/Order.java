package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "shopping")
public class Order {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_store", referencedColumnName = "id")
    private Store store;

}
