package com.phonebook.phonebook.controller;

import com.phonebook.phonebook.model.PhoneBook;
import com.phonebook.phonebook.model.Role;
import com.phonebook.phonebook.model.User;
import com.phonebook.phonebook.service.PhoneBookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;


public class IndexControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private PhoneBookService phoneBookService;

    @InjectMocks
    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    }

    @Test
    public void start() throws Exception {

        User user = User.builder()
                .id(1L).name("name").login("login").surname("surname").patronymic("patronymic").password("password")
                .authorities(Collections.singleton(Role.USER)).
                        accountNonExpired(true).credentialsNonExpired(true).accountNonLocked(true).enabled(true)
                .build();


        PhoneBook one = PhoneBook.builder()
                .id(1L).name("name").surname("surname").patronymic("patronymic").phoneMobile("phoneMobile")
                .phoneHome("phoneHome").address("address").email("email").user(user).build();

        PhoneBook two = PhoneBook.builder()
                .id(2L).name("name").surname("surname").patronymic("patronymic").phoneMobile("phoneMobile")
                .phoneHome("phoneHome").address("address").email("email").user(user).build();

        when(phoneBookService.getAllUserBookId(user.getId())).thenReturn(Arrays.asList(one, two));
    }

}