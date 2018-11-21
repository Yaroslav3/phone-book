package com.phonebook.phonebook.controller;


import com.phonebook.phonebook.model.PhoneBook;
import com.phonebook.phonebook.model.User;
import com.phonebook.phonebook.service.impl.PhoneBookServiceImpl;
import com.phonebook.phonebook.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PhoneBookServiceImpl phoneBookService;

    @Autowired
    private UserServiceImpl userService;

    private User getUserAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @RequestMapping("/")
    public String start(Model model, PhoneBook phoneBook) {
        getAllPhoto(model);
        return "index";
    }


    @ModelAttribute("phoneBook_session")
    public HttpSession sessionUser(HttpSession httpSession) {
        httpSession.setAttribute("phoneBook_session", new PhoneBook());
        return httpSession;
    }

    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute("phoneBook") PhoneBook phoneBook, BindingResult result,
                       @RequestParam(value = "name") String name,
                       @RequestParam(value = "surname") String surname,
                       @RequestParam(value = "patronymic") String patronymic,
                       @RequestParam(value = "phoneMobile") String phoneMobile,
                       @RequestParam(value = "phoneHome") String phoneHome,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "address") String address, Model model, RedirectAttributes redirectAttributes) {

        logger.info("begin");

        if (result.hasErrors()) {
            getUser(model);
            getAllPhoto(model);
            logger.info("test - error");
            return "index";
        }

        logger.info("test - save");
        phoneBookService.save(PhoneBook.builder()
                .address(address)
                .email(email)
                .name(name)
                .patronymic(patronymic)
                .phoneMobile(phoneMobile)
                .phoneHome(phoneHome)
                .surname(surname)
                .user(userService.getUser(getUserAccount().getLogin())).build());
        getUser(model);
        redirectAttributes.addAttribute("message_save_phone", "User added successfully!");
        return "redirect:/";
    }

    @GetMapping("/getOnePhone/{id}")
    @ResponseBody
    public Optional<PhoneBook> getOnePhone(@ModelAttribute("phoneBook") PhoneBook phoneBook, @PathVariable("id") Long
            id) {
        return phoneBookService.findOnePhoneBook(id);
    }

    @GetMapping("/deletePhone/{id}")
    public String deletePhone(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        phoneBookService.delete(id);
        redirectAttributes.addFlashAttribute("susses_delete", " post deleted");
        return "redirect:/";
    }

    @PostMapping("/updatePhone/{id}")
    public String updatePhone(@ModelAttribute("phoneBook") PhoneBook phoneBook,
                              @PathVariable(value = "id") Long id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "surname") String surname,
                              @RequestParam(value = "patronymic") String patronymic,
                              @RequestParam(value = "phoneMobile") String phoneMobile,
                              @RequestParam(value = "phoneHome") String phoneHome,
                              @RequestParam(value = "email") String email,
                              @RequestParam(value = "address") String address) {

        phoneBookService.save(PhoneBook.builder()
                .id(id)
                .address(address)
                .email(email)
                .name(name)
                .patronymic(patronymic)
                .phoneMobile(phoneMobile)
                .phoneHome(phoneHome)
                .surname(surname)
                .user(userService.getUser(getUserAccount().getLogin())).build());
        return "redirect:/";
    }


    @PostMapping("/filter")
    public String filter(@ModelAttribute("phoneBook") PhoneBook phoneBook,
                         @RequestParam(value = "filter") String filter, Model model) {

        if (filter.equals("name")) {
            logger.info("name");
            getUser(model);
            model.addAttribute("phoneBookAll", phoneBookService.getAllPhoneBookName(userService.getUser(getUserAccount().getLogin()).getId()));
            return "index";
        }
        if (filter.equals("surname")) {
            getUser(model);
            logger.info("surname");
            model.addAttribute("phoneBookAll", phoneBookService.getAllBookSurname(userService.getUser(getUserAccount().getLogin()).getId()));
            return "index";
        }
        if (filter.equals("phoneMobile")) {
            getUser(model);
            logger.info("phoneMobile");
            model.addAttribute("phoneBookAll", phoneBookService.getAllBookPhoneMobile(userService.getUser(getUserAccount().getLogin()).getId()));
            return "index";
        }

        return "redirect:/";

    }

    private void getAllPhoto(Model model) {
        model.addAttribute("phoneBookAll", phoneBookService.getAllUserBookId(
                userService.getUser(getUserAccount().getLogin()).getId()));


    }

    private void getUser(Model model) {
        User user = getUserAccount();
        model.addAttribute("user_mame", user.getName());
    }
}
