package com.monster.repositories;

import com.monster.models.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonsterRepository extends MongoRepository<Monster, String> {
}
