package home.login.repositories;

import home.login.entities.User;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Component
public interface UserRepository extends Repository {

    User findByUsername(String username);

    void save(User user);

}
