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
import java.security.Principal;
import java.util.Collections;

@Controller
@RequestMapping
public class LoginController {


    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {


        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        model.addAttribute("massage_logout", "You've been logged out successfully.");
        model.addAttribute("massage_error", "Invalid name or password");
        return "authentication";
    }


}
