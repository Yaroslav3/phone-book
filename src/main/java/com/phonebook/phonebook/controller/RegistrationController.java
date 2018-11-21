package com.phonebook.phonebook.controller;

import com.phonebook.phonebook.model.Role;
import com.phonebook.phonebook.model.User;
import com.phonebook.phonebook.service.UserService;
import com.phonebook.phonebook.service.impl.PasswordEncoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;

@Controller

public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    private UserService userService;


    @RequestMapping("/registration")
    public String registration(User user, Model model) {
        return "registration";
    }


    @ModelAttribute("user_session")
    public HttpSession sessionUser(HttpSession httpSession) {
        httpSession.setAttribute("user_session", new User());
        return httpSession;
    }


    @PostMapping(value = "/registration/save")
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "surname") String surname,
                       @RequestParam(value = "patronymic") String patronymic,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "login") String login, Model model) {

        logger.info("test2");

        if (result.hasErrors()) {
            logger.info("test - error-user");
            return "registration";
        }


        try {
            user = userService.getUser(login);
            logger.info(user.getLogin());
            if (user.getLogin().equals(login)) {
                model.addAttribute("error_name", true);
                model.addAttribute("message_error", "such login exists, choose another login: " + " ' " + login + " ' ");
            }

        } catch (NullPointerException e) {
            userService.save(User.builder()
                    .name(name)
                    .login(login)
                    .surname(surname)
                    .patronymic(patronymic)
                    .password(passwordEncoderService.bCryptEncoder(password))
                    .authorities(Collections.singleton(Role.USER))
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .enabled(true)
                    .build());
            logger.info("test - save user");
            model.addAttribute("successful_registration", true);
            model.addAttribute("message_successful", "user is registered" + " ' " + login + " ' ");
        }
        logger.info("test3");
        return "registration";
    }


}
