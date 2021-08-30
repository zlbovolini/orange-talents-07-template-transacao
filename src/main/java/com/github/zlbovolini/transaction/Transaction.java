package com.github.zlbovolini.transaction;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;

// Poderia ser composta de entidades embedded, já que os dados nunca serão atualizados.
// Ou utilizar um banco de dados NoSQL.
@Entity
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private UUID uuid;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime accomplishAt;

    @Valid
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = PERSIST)
    private Store store;

    @Valid
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = PERSIST)
    private Card card;

    @Deprecated
    Transaction() {}

    Transaction(UUID uuid, BigDecimal amount, LocalDateTime accomplishAt, Store store, Card card) {
        this.uuid = uuid;
        this.amount = amount;
        this.accomplishAt = accomplishAt;
        this.store = store;
        this.card = card;
    }

    UUID getUuid() {
        return uuid;
    }

    BigDecimal getAmount() {
        return amount;
    }

    LocalDateTime getAccomplishAt() {
        return accomplishAt;
    }

    Store getStore() {
        return store;
    }

    Card getCard() {
        return card;
    }
}
