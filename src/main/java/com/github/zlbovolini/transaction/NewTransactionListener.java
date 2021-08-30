package com.github.zlbovolini.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.Valid;

@Service
class NewTransactionListener {

    private final Logger logger = LoggerFactory.getLogger(NewTransactionListener.class);

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final StoreRepository storeRepository;
    private final TransactionTemplate transactionTemplate;

    NewTransactionListener(TransactionRepository transactionRepository,
                           CardRepository cardRepository,
                           StoreRepository storeRepository,
                           TransactionTemplate transactionTemplate) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.storeRepository = storeRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.transactions}",
            containerFactory = "transactionKafkaListenerContainerFactory")
    void consume(@Valid TransactionPayload transactionPayload) {
        logger.info("Received new transaction uuid={}", transactionPayload.getUuid());

        transactionTemplate.execute(status -> {
            Transaction transaction = transactionPayload.toModel(storeRepository::findOne, cardRepository::findOne);
            return transactionRepository.save(transaction);
        });
    }
}
