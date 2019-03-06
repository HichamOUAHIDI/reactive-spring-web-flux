package org.reactive.dao;

import org.reactive.entites.Societe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SocieteRepsitory extends ReactiveMongoRepository<Societe, String> {
	

}
