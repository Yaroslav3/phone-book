package com.phonebook.phonebook.service.impl;


import com.phonebook.phonebook.model.User;
import com.phonebook.phonebook.repository.UserRepository;
import com.phonebook.phonebook.service.UserService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(@NonNull String login) throws UsernameNotFoundException {

        try {
            User byUsername = userRepository.getUserLogin(login);
            if (byUsername.getLogin() != null) {
                return User.builder()
                        .password(byUsername.getPassword())
                        .name(byUsername.getUsername())
                        .login(login)
                        .authorities(byUsername.getAuthorities())
                        .accountNonExpired(true)
                        .credentialsNonExpired(true)
                        .accountNonLocked(true)
                        .enabled(true)
                        .build();

            } else {
                throw new NullPointerException(login);
            }
        }catch (NullPointerException notFound) {
            logger.info("login not fount");
            throw new UsernameNotFoundException("login not fount");
        }
    }



    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String login) {
        return userRepository.getUserLogin(login);
    }


}
