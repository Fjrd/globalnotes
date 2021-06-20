package com.example.globalnotes.controllers;


import com.example.globalnotes.dao.UserDao;
import com.example.globalnotes.model.Note;
import com.example.globalnotes.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @NonNull
    UserDao userDao;


    public void save(){
        userDao.saveAll(Collections.singletonList(new User("login", "pass", "name", "email")));

    }

    @GetMapping
    public String getAllUsers(Model model){
        List<User> userList = userDao.findAll();
        model.addAttribute("users", userList);
        return "user/UserList";
    }

    @GetMapping("/new")
    public String addNewNoteForm(){
        return "user/AddNewUserForm";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult){
        save();
        if (bindingResult.hasErrors()) {
            return "user/AddNewUserForm";
        }
        userDao.saveAll(Collections.singletonList(user));
        return "redirect:/users";
    }
}
