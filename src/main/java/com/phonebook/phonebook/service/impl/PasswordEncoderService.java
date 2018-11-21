package com.phonebook.phonebook.service.impl;

import com.phonebook.phonebook.service.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordEncoderService implements PasswordEncoder {
    private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();


    @Override
    public String bCryptEncoder(String password) {
        return bCrypt.encode(password);
    }

    @Override
    public boolean bCryptMatches(CharSequence password, String encode) {
        return bCrypt.matches(password, encode);
    }

}
