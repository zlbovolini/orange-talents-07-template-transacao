package com.github.zlbovolini.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @Deprecated
    Store() {}

    Store(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }
}
