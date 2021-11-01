package com.example.codefellowship.Controllers;


import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class AppUserControllers {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal){
        ApplicationUser applicationUser=appUserRepository.findByUsername(principal.getName());
        model.addAttribute("userName",applicationUser);
        return"profile";
    }

    @GetMapping("/users/{id}")
    public String findingUser(@PathVariable int id,Model model){
        model.addAttribute("userName",appUserRepository.findAll());
        return"profile";
    }

    @PostMapping("/signup")
    public RedirectView signUpPage(@RequestParam String username,
                                   @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
                                   @RequestParam String dateOfBirth, @RequestParam String bio,@RequestParam String imageUrl     ){

        ApplicationUser applicationUser=new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio,imageUrl);
        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        appUserRepository.save(applicationUser);
        return new RedirectView("/profile");

    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/Components")
    public String Fragments(){
        return"components.html";

    }





}
