package com.demo.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entites.Societe;
import com.demo.entites.Transaction;
import com.demo.web.SocieteReactiveController;

import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction,String>{
     public Flux<Transaction> findBySociete(Societe societe);
}
