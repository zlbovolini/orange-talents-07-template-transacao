package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

class StorePayload {

    @NotBlank
    private final String name;

    @NotBlank
    private final String city;

    @NotBlank
    private final String address;

    @JsonCreator(mode = PROPERTIES)
    StorePayload(
            @JsonProperty("nome") String name,
            @JsonProperty("cidade") String city,
            @JsonProperty("endereco") String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    Store toModel() {
        return new Store(name, city, address);
    }
}
