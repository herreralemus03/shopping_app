package com.hl.shopping.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries", schema = "shopping")
public class Country {

    @Id
    @Getter @Setter
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "country_name")
    private String countryName;

    @Getter @Setter
    @Column(name = "country_code")
    private String countryCode;

    @Getter @Setter
    @Column(name = "country_alias")
    private String countryAlias;

}
