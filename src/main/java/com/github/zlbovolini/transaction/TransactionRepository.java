package com.github.zlbovolini.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByCardId(Long cardId, Pageable pageable);
}
