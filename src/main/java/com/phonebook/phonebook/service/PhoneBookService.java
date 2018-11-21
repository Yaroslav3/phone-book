package com.phonebook.phonebook.service;

import com.phonebook.phonebook.model.PhoneBook;

import java.util.List;
import java.util.Optional;

public interface PhoneBookService {

    void save(PhoneBook phoneBook);

    void delete(Long id);

    PhoneBook update(PhoneBook phoneBook);

    Optional<PhoneBook> findOnePhoneBook(Long id);

    List<PhoneBook> getAllUserBookId(Long id);

    List<PhoneBook> getAllPhoneBookName(Long id);

    List<PhoneBook> getAllBookSurname(Long id);

    List<PhoneBook> getAllBookPhoneMobile(Long id);

}
