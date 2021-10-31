package com.example.codefellowship.Controllers;


import com.example.codefellowship.Model.ApplicationUser;
import com.example.codefellowship.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppUserControllers {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/signUp")
    public String getSignUpPage(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpPage(@RequestParam String userName,
                             @RequestParam String password,@RequestParam String firstName,@RequestParam String lastName,
                             @RequestParam int dateOfBirth,@RequestParam String bio){

        ApplicationUser applicationUser=new ApplicationUser(userName,encoder.encode(password),firstName,lastName,dateOfBirth,bio);

        appUserRepository.save(applicationUser);
        return "login";


    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }




}
