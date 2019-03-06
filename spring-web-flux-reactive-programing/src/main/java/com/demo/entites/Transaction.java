package com.demo.entites;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @ToString
@Document
public class Transaction {
	@Id
	private String id; 
	private Instant instant;
	private double price; 
	@DBRef
	// ignoré l'affichage de la liste de sosicte 
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private Societe societe;
}
