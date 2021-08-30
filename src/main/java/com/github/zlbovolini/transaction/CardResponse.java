package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
class CardResponse {

    private final UUID uuid;
    private final String email;

    CardResponse(Card card) {
        this.uuid = card.getUuid();
        this.email = card.getEmail();
    }
}
