package com.example.codefellowship.controllers;

import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getSignInPage() {
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signUp(@ModelAttribute ApplicationUser myUser
    ) {

        myUser.setPassword(encoder.encode(myUser.getPassword()));
        applicationUserRepository.save(myUser);
        return new RedirectView("/myProfile");
    }


    @GetMapping("/users/{id}")
    public String getProfile(@PathVariable("id") Long id, Model model) {
        ApplicationUser user = applicationUserRepository.findById(id);
        model.addAttribute("user", user);
        return "profile";
    }

        @GetMapping("/user")
    public String getProfile(Model m, Principal p) {
        if (p.getName() == null){
            return "login.html";
        }
        m.addAttribute("user", applicationUserRepository.findByUsername(p.getName()));
        return "home.html";
    }

    @RequestMapping("/allProviders")
    String viewRenderPage(Model model){
        List<ApplicationUser> listProvider = applicationUserRepository.findAll();
        model.addAttribute("SerProvList",listProvider);
        return "users";
    }

}
