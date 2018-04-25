package com.alternate.sample.controllers;

import com.alternate.sample.models.User;
import com.alternate.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService; //further reading: attribute autowiring is not recommended

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user")User user, Model model) {
        if (userService.login(user.getUsername(), user.getPassword())) {
            model.addAttribute("message", "Hello, " + user.getUsername() + ". Login successful");
        } else {
            model.addAttribute("message", "Login unsuccessful");
        }
        return "notification";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user")User user, Model model) {
        if (userService.register(user)) {
            model.addAttribute("message", "User registered");
        } else {
            model.addAttribute("message", "User not registered");
        }
        return "notification";
    }
}
