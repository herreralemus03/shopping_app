package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products", schema = "shopping")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter @Setter
    UUID id;

    @Getter @Setter
    @Column(name = "product_name")
    String productName;

    @Getter @Setter
    @Column(name = "product_description")
    String productDescription;

    @Getter @Setter
    @Column(name = "weight_unit")
    String weightUnit;

    @Getter @Setter
    @Column(name = "weight")
    Float weight;

    @Getter @Setter
    @Column(name = "price")
    Float price;

}
