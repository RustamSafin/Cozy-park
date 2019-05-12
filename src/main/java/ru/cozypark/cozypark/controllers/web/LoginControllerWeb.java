package ru.cozypark.cozypark.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.cozypark.cozypark.payloads.SignUpRequest;
import ru.cozypark.cozypark.service.UserService;

@Controller
@RequestMapping
public class LoginControllerWeb {

    private final UserService service;

    public LoginControllerWeb(UserService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/reg")
    public String reg(){

        return "reg";
    }

    @PostMapping("/reg")
    public String regPost(SignUpRequest signUpRequest){
        if (service.existsByUsername(signUpRequest.getUsername())) {
            return "reg";
        }

        if (service.existsByEmail(signUpRequest.getEmail())) {
            return "reg";
        }

        service.signUp(signUpRequest);

        return "redirect:/issue";
    }
}
