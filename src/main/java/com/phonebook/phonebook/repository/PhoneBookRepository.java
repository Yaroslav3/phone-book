package com.phonebook.phonebook.repository;


import com.phonebook.phonebook.model.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

    @Query(value = "SELECT * FROM phonebook.phone_book WHERE user_id=?1", nativeQuery = true)
    List<PhoneBook> getAllPhoneBook(Long id);
}
