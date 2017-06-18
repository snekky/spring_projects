package home.login.services;

import home.login.entities.Role;
import home.login.entities.User;
import home.login.entities.UserField;
import home.login.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @PostConstruct
    public void init() {

        userRepository.findByUsername("user").ifPresent(user -> {
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            userRepository.save(user);
        });
        ArrayList<Role> role = new ArrayList<>();
        role.add(Role.USER);
        if (!userRepository.findByUsername("user").isPresent()) {
            user.setUsername(UserField.USER_NAME.field());
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            user.setAuthorities(role);
            user.setAccountNonLocked(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
        }



    }

    @Override
    public UserDetails loadUserByUsername( String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}
