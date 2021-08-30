package com.github.zlbovolini.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/v1/cards")
public class FindTransactionController {

    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    public FindTransactionController(CardRepository cardRepository,
                                     TransactionRepository transactionRepository) {
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/{uuid}/transactions")
    @PreAuthorize("isCardOwner(#uuid)")
    public ResponseEntity<Page<TransactionResponse>> find(@PathVariable UUID uuid) {
        return cardRepository.findIdByUuid(uuid)
                .map(this::findLastTenTransactions)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private Page<TransactionResponse> findLastTenTransactions(CardIdProjection cardIdProjection) {
        Pageable pageable = PageRequest.of(0, 10, DESC, "accomplishAt");

        return transactionRepository.findByCardId(cardIdProjection.getId(), pageable)
                .map(TransactionResponse::new);
    }
}
