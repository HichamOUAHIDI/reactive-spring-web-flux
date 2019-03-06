package com.demo.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.demo.entites.Societe;

public interface SocieteRepository extends ReactiveMongoRepository<Societe, String>{

}
