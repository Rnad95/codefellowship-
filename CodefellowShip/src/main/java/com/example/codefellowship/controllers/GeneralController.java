package com.example.codefellowship.controllers;

import com.example.codefellowship.models.Post;
import com.example.codefellowship.repositories.ApplicationUserRepository;
import com.example.codefellowship.models.ApplicationUser;
import com.example.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class GeneralController {


    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostRepository postRepository;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/home")
    public String getHome(Principal p , Model model){
        if(p!=null){
            model.addAttribute("user", applicationUserRepository.findByUsername(p.getName()));
            model.addAttribute("userName", p.getName());
        }
        return "home";
    }

    @GetMapping("/myProfile")
    public String myProfile(){

        return "myProfile";
    }

    @GetMapping("/users")
    String String (Principal principal, Model model) {
        if (principal.getName() == null){
            return "signin";
        }
        model.addAttribute("users", applicationUserRepository.findAll());
        return "home";
    }



    @PostMapping("/post")
    public RedirectView addpost(Principal p, String body) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        Post newPost = new Post(body, user);
        postRepository.save(newPost);
        return new RedirectView("/home");
    }

    @PostMapping("/post/")
    public RedirectView newPost(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "body") String body
    ) {
        ApplicationUser user = applicationUserRepository.findById(id).get();
        Post newPost = new Post(body, user);
        postRepository.save(newPost);
        return new RedirectView("/");
    }




}
