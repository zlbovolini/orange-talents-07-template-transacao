package com.github.zlbovolini.transaction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

class TransactionPayload {

    @NotNull
    private final UUID uuid;

    @NotNull
    private final BigDecimal amount;

    @Valid
    @NotNull
    private final StorePayload storePayload;

    @Valid
    @NotNull
    private final CardPayload cardPayload;

    @NotNull
    private final LocalDateTime accomplishAt;

    @JsonCreator(mode = PROPERTIES)
    TransactionPayload(
            @JsonProperty("id") UUID uuid,
            @JsonProperty("valor") BigDecimal amount,
            @JsonProperty("estabelecimento") StorePayload storePayload,
            @JsonProperty("cartao") CardPayload cardPayload,
            @JsonProperty("efetivadaEm") LocalDateTime accomplishAt) {
        this.uuid = uuid;
        this.amount = amount;
        this.storePayload = storePayload;
        this.cardPayload = cardPayload;
        this.accomplishAt = accomplishAt;
    }

    UUID getUuid() {
        return uuid;
    }

    /**
     * Never updates database data
     * @param findStore
     * @param findCard
     * @return
     */
    Transaction toModel(Function<Example<Store>, Optional<Store>> findStore,
                        Function<Example<Card>, Optional<Card>> findCard) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIncludeNullValues()
                .withIgnorePaths("id");

        Store transientStore = storePayload.toModel();
        Store store = findStore.apply(Example.of(transientStore, exampleMatcher))
                .orElse(transientStore);

        Card transientCard = cardPayload.toModel();
        Card card = findCard.apply(Example.of(transientCard, exampleMatcher))
                .orElse(transientCard);

        return new Transaction(uuid, amount, accomplishAt, store, card);
    }
}
