package com.luv2code.jobportal.controller;

import com.luv2code.jobportal.entity.Users;
import com.luv2code.jobportal.entity.UsersType;
import com.luv2code.jobportal.services.UsersService;
import com.luv2code.jobportal.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;
    private final View error;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService, View error) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
        this.error = error;
    }
    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes",usersTypes);
        model.addAttribute("user",new Users());
        return "register";
    }
    @PostMapping( "/register/new")
    public String UserRegistration(@Validated  Users users, Model model){
        Optional<Users> optionalUsers = usersService.getUserByEmail(users.getEmail());

        if(optionalUsers.isPresent()){
            model.addAttribute("error","Email already exists,try to login or register with another email.");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes",usersTypes);
            model.addAttribute("user",new Users());
            return "register";
        }
        usersService.addNew(users);
        return "redirect:/dashboard/";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";

    }
}