package com.demo.entites;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Document
@ToString
public class Societe {
    
	@Id
	private String id; 
	private String name; 
	private double price; 

}
