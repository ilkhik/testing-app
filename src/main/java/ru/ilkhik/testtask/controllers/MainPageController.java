package ru.ilkhik.testtask.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ilkhik.testtask.config.details.UserDetailsImpl;
import ru.ilkhik.testtask.models.User;

import java.time.format.DateTimeFormatter;

@Controller
public class MainPageController {
    @RequestMapping("/")
    public String getMainPage(Authentication authentication, Model model) {
        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        model.addAttribute("signupDate", user.getSignupDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        model.addAttribute("user", user);
        return "main";
    }
}
