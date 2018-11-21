package com.phonebook.phonebook.service;

import com.phonebook.phonebook.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    User getUser(String login);
}
