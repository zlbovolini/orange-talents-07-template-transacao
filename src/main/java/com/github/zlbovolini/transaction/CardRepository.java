package com.github.zlbovolini.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface CardRepository extends JpaRepository<Card, Long> {

    Optional<CardIdProjection> findByUuid(UUID uuid);
}
