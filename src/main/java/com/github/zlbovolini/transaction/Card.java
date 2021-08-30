package com.github.zlbovolini.transaction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
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

    void setEmail(String email) {
        this.email = email;
    }
}
