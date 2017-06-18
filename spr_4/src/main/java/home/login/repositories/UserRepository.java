package home.login.repositories;

import home.login.entities.User;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<UserRepository> findByUsername(String username);



}
