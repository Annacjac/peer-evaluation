package edu.tcu.cs.peerevalbackend;

import edu.tcu.cs.peerevalbackend.section.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PeerEvalBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeerEvalBackendApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1, 1);
	}

}
