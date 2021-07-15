package com.example.globalnotes.controllers;


import com.example.globalnotes.model.User;
import com.example.globalnotes.repository.UserRepository;
import com.example.globalnotes.web.UserSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @NonNull
    UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/UserList";
    }

    @GetMapping("/new")
    public String showRegistrationForm(){
        return "user/RegistrationForm";
    }

    @PostMapping()
    public String handleRegistration(@ModelAttribute("user") @Valid User user,
                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/RegistrationForm";
        }
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "user/LoginForm";
    }

    @PostMapping("/login")
    public String handleLoginForm(@RequestParam String login,
                                  @RequestParam String password,
                                  UserSession userSession){
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user != null){
            userSession.setId(user.getId());
            userSession.setLogin(user.getLogin());
            return "redirect:/";
        }
        else return "redirect:/users/login";
    }
}
