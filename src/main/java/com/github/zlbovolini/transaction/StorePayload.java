package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.function.Function;

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

    Store toModel(Function<Example<Store>, Optional<Store>> findStore) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withIgnorePaths("id");

        Store transientStore = new Store(name, city, address);

        return findStore.apply(Example.of(transientStore, exampleMatcher))
                .orElse(transientStore);
    }
}
