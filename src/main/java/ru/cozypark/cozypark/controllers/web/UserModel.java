package ru.cozypark.cozypark.controllers.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.cozypark.cozypark.models.User;

@ControllerAdvice
public class UserModel {
    @ModelAttribute("user")
    public User setUserToModel() {
        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            return new User();
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
