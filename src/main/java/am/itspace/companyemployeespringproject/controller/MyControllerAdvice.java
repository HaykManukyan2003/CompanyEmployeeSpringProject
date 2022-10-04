package am.itspace.companyemployeespringproject.controller;

import am.itspace.companyemployeespringproject.entity.User;
import am.itspace.companyemployeespringproject.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return currentUser == null ? null : currentUser.getUser();
    }
}
