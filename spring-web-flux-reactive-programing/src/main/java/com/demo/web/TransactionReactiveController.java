package com.demo.web;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.SocieteRepository;
import com.demo.dao.TransactionRepository;
import com.demo.entites.Societe;
import com.demo.entites.Transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TransactionReactiveController {
 @Autowired
 private TransactionRepository transactionRepository;
 @Autowired
 private SocieteRepository societeRepository;
 
 @GetMapping(value="/transactions")
 public Flux<Transaction> findAll() {
	 return transactionRepository.findAll();
 }
 @GetMapping(value="/transactions/{id}")
 public Mono<Transaction> getOne(@PathVariable String id) {
	 return transactionRepository.findById(id);
 }
 @PostMapping(value="/transactions")
 public Mono<Transaction> save(@RequestBody Transaction transaction){
	 return transactionRepository.save(transaction);
 }
 @DeleteMapping(value="/transactions/{id}")
 public Mono<Void> delete(@PathVariable String id){
	 return transactionRepository.deleteById(id);
 }
 @PutMapping(value="/transactions/{id}")
 public Mono<Transaction> update(@RequestBody Transaction transaction,@PathVariable String id){
	 transaction.setId(id);
	 return transactionRepository.save(transaction);
 }
 // je dis a spring voilla envoyé moi le resulta sous forme d'un text envent stream
 @GetMapping(value="/streamTransactions",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
 public Flux<Transaction> streamTransactions() {
	 return transactionRepository.findAll();
 }
 @GetMapping(value="/transactionsBySociete/{id}")
 public Flux<Transaction> transactionBySociete(@PathVariable String id) {
	 Societe societe = new Societe();
	 societe.setId(id);
	 return transactionRepository.findBySociete(societe);
 }
 
 @GetMapping(value="/streamTransactionsBySociete/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
 public Flux<Transaction> streamTransactionsBySociete(@PathVariable String id) {
	return societeRepository.findById(id).flatMapMany(soc ->{
		Flux<Long> interval =  Flux.interval(Duration.ofMillis(1000));
	    Flux<Transaction> transactionFlux = Flux.fromStream(Stream.generate(()-> {
	    	Transaction transaction = new Transaction();
	    	transaction.setInstant(Instant.now());
	    	transaction.setPrice(soc.getPrice());
	    	transaction.setSociete(soc);
	    	return transaction;
	    }));
		 return Flux.zip(interval,transactionFlux)
				 .map(d -> {
					 return d.getT2();
				 }).share();
	});
 }
}
