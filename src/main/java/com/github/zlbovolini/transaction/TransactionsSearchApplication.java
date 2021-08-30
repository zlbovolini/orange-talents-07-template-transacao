package com.github.zlbovolini.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@EnableJpaRepositories(enableDefaultTransactions = false)
@SpringBootApplication
public class TransactionsSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsSearchApplication.class, args);
	}

}
