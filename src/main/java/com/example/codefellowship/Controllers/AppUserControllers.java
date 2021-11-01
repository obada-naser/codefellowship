package com.example.codefellowship.Controllers;


import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class AppUserControllers {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal){
        ApplicationUser applicationUser=appUserRepository.findByUserName(principal.getName());
        model.addAttribute("userName",applicationUser);
        return"profile";
    }

    @GetMapping("/users/{id}")
    public String findingUser(@PathVariable int id,Model model){
        model.addAttribute("userName",appUserRepository.findById(id));
        return"profile";
    }

    @PostMapping("/signup")
    public RedirectView signUpPage(@RequestParam String userName,
                                   @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
                                   @RequestParam String dateOfBirth, @RequestParam String bio){

        ApplicationUser applicationUser=new ApplicationUser(userName,encoder.encode(password),firstName,lastName,dateOfBirth,bio);

        appUserRepository.save(applicationUser);
        return new RedirectView("/login");


    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }




}
