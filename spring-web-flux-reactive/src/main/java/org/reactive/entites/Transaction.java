package org.reactive.entites;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Transaction {
	
	@Id
	private String id; 
	private Instant instant;
	private double price; 
	@DBRef
	private Societe societe;

}
