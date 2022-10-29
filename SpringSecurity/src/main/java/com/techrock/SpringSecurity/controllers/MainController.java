package com.techrock.SpringSecurity.controllers;

import com.techrock.SpringSecurity.entities.User;
import com.techrock.SpringSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return "secured part of web service: " + user.getUsername() + " " + user.getEmail();
    }
    @GetMapping("/read_profile")
    public String pageForReadProfile(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "read profile page";
    }
    @GetMapping("/only_for_admins")
    public String pageForOnlyForAdmins(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "admins page";
    }
}
