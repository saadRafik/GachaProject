package com.auth.repositories;

import com.auth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    // We'll look up the user by the embedded token's value
    @Query("{ 'token.value': ?0 }")
    Optional<User> findByTokenValue(String tokenValue);
}
