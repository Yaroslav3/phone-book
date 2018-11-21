package com.phonebook.phonebook.service;

public interface PasswordEncoder {

    /**
     * Generate crypt password BCryptPasswordEncoder
     **/
    String bCryptEncoder(String password);


    /**
     * Check password BCryptPasswordEncoder
     **/
    boolean bCryptMatches(CharSequence password, String encode);
}
