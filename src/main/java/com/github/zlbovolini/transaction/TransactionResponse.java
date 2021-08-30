package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
class TransactionResponse {

    private final UUID uuid;
    private final BigDecimal amount;
    private final StoreResponse store;
    private final CardResponse card;
    private final LocalDateTime accomplishAt;

    TransactionResponse(Transaction transaction) {
        this.uuid = transaction.getUuid();
        this.amount = transaction.getAmount();
        this.store = new StoreResponse(transaction.getStore());
        this.card = new CardResponse(transaction.getCard());
        this.accomplishAt = transaction.getAccomplishAt();
    }
}
