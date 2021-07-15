package com.example.globalnotes.controllers;

import com.example.globalnotes.web.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainPageController {

    @GetMapping
    public String index(UserSession userSession){
        return "index";
    }

    @ModelAttribute("userSession")
    public UserSession createUserSession() {
        return new UserSession();
    }
}
