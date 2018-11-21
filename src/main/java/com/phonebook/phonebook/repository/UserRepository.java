package com.phonebook.phonebook.repository;

import com.phonebook.phonebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM phonebook.user WHERE login=?1", nativeQuery = true)
    User getUserLogin(String login);
}
