package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

class CardPayload {

    @NotNull
    private final UUID uuid;

    @NotBlank
    private final String email;

    @JsonCreator(mode = PROPERTIES)
    CardPayload(
            @JsonProperty("id") UUID uuid,
            @JsonProperty("email") String email) {
        this.uuid = uuid;
        this.email = email;
    }

    Card toModel(Function<UUID, Optional<Card>> findCard) {
        return findCard.apply(uuid)
                .map(card -> {
                    card.setEmail(email);
                    return card;
                })
                .orElse(new Card(uuid, email));
    }

    UUID getUuid() {
        return uuid;
    }
}
