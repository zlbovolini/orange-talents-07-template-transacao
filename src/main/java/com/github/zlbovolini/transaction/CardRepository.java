package com.github.zlbovolini.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

interface CardRepository extends JpaRepository<Card, Long> {
}
