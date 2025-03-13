package com.monster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		excludeName = {
				"org.springframework.ai.autoconfigure.vectorstore.mongo.MongoDBAtlasVectorStoreAutoConfiguration"
		}
)
public class MonsterApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonsterApiApplication.class, args);
	}
}
