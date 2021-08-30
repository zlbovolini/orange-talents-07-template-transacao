package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
class StoreResponse {

    private final String name;
    private final String city;
    private final String address;

    StoreResponse(Store store) {
        this.name = store.getName();
        this.city = store.getCity();
        this.address = store.getAddress();
    }
}
