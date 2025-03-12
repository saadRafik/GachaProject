package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		excludeName = {
				"org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration"
		}
)
public class AuthentificationApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthentificationApiApplication.class, args);
	}
}

