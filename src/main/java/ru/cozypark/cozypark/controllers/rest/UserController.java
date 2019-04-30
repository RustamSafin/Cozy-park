package ru.cozypark.cozypark.controllers.rest;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.SignUpRequest;
import ru.cozypark.cozypark.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public User user(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    @PatchMapping
    public User userUpdate(@RequestBody SignUpRequest request, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if (user.getUsername().equals(request.getUsername())) {
           return service.signUp(request);
        }
        return user;
    }
}
