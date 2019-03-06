package org.reactive;

import org.reactive.dao.SocieteRepsitory;
import org.reactive.dao.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mongodb.connection.Stream;

@SpringBootApplication
public class SpringWebFluxReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebFluxReactiveApplication.class, args);
	} 
	
	@Bean
	CommandLineRunner start (SocieteRepsitory societeRepsitory,
			TransactionRepository transactionRepository) {
		
		return args->{     
		   Stream.of("SG","AWB","AXA").forEach(s-> {
			   societeRepsitory.save(new Societe(s,s,100+Math.random()*900));
		       .subscribe(soc -> {
		    	  System.out.println(soc.toString());
		       });
		   });	
		}
	}
}

