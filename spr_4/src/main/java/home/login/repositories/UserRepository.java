package home.login.repositories;

import home.login.entities.User;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;




@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

}
