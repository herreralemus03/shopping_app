package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shipping_articles", schema = "shopping")
public class ShippingArticle {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    private UUID id;

    @Getter @Setter
    @Column(name = "weight")
    private Float weight;

    @Getter @Setter
    @Column(name = "weight_unit")
    private String weightUnit;

    @Getter @Setter
    @Column(name = "article_notes")
    private String articleNotes;

    @Getter @Setter
    @Column(name = "amount")
    private Long amount;

}
