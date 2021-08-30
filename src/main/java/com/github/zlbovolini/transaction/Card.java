package com.github.zlbovolini.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID uuid;

    @NotNull
    private String email;

    @Deprecated
    Card() {}

    Card(UUID uuid, String email) {
        this.uuid = uuid;
        this.email = email;
    }

    UUID getUuid() {
        return uuid;
    }

    String getEmail() {
        return email;
    }
}
