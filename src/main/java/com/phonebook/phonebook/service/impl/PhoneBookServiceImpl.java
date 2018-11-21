package com.phonebook.phonebook.service.impl;

import com.phonebook.phonebook.model.PhoneBook;
import com.phonebook.phonebook.repository.PhoneBookRepository;
import com.phonebook.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {


    private PhoneBookRepository phoneBookRepository;

    @Autowired
    public void setPhoneBookRepository(PhoneBookRepository phoneBookRepository) {
        this.phoneBookRepository = phoneBookRepository;
    }


    @Override
    public void save(PhoneBook phoneBook) {
        phoneBookRepository.save(phoneBook);
    }

    @Override
    public void delete(Long id) {
        phoneBookRepository.deleteById(id);
    }

    @Override
    public PhoneBook update(PhoneBook phoneBook) {
        return phoneBookRepository.saveAndFlush(phoneBook);
    }


    @Override
    public Optional<PhoneBook> findOnePhoneBook(Long id) {
        return phoneBookRepository.findById(id);
    }

    @Override
    public List<PhoneBook> getAllUserBookId(Long id) {
        return phoneBookRepository.getAllPhoneBook(id).stream()
                .sorted(Comparator.comparing(PhoneBook::getId)).collect(Collectors.toList());
    }

    @Override
    public List<PhoneBook> getAllPhoneBookName(Long id) {
        return phoneBookRepository.getAllPhoneBook(id).stream()
                .sorted(Comparator.comparing(PhoneBook::getName)).collect(Collectors.toList());
    }


    @Override
    public List<PhoneBook> getAllBookSurname(Long id) {
        return phoneBookRepository.getAllPhoneBook(id).stream()
                .sorted(Comparator.comparing(PhoneBook::getSurname)).collect(Collectors.toList());
    }

    @Override
    public List<PhoneBook> getAllBookPhoneMobile(Long id) {
        return phoneBookRepository.getAllPhoneBook(id).stream()
                .sorted(Comparator.comparing(PhoneBook::getPhoneMobile)).collect(Collectors.toList());
    }


}
