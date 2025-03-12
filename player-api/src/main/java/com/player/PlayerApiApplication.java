package com.player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		excludeName = {
				"org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration"
		}
)
public class PlayerApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlayerApiApplication.class, args);
	}
}
